package tm.server;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.entity.Session;
import tm.common.entity.User;
import tm.server.api.ServiceLocator;
import tm.common.api.entity.PlannedEntity;
import tm.server.api.service.*;
import tm.server.command.AbstractCommand;
import tm.server.repository.jdbc.ProjectRepositoryMySQL;
import tm.server.repository.jdbc.SessionRepositoryMySQL;
import tm.server.repository.jdbc.TaskRepositoryMySQL;
import tm.server.repository.jdbc.UserRepositoryMySQL;
import tm.server.repository.mybatis.ProjectRepositoryMyBatis;
import tm.server.repository.mybatis.SessionRepositoryMyBatis;
import tm.server.repository.mybatis.TaskRepositoryMyBatis;
import tm.server.repository.mybatis.UserRepositoryMyBatis;
import tm.server.service.*;
import tm.server.utils.CypherUtil;
import tm.server.utils.DatabaseConnectionUtil;
import tm.server.utils.InputHelper;
import tm.common.comparator.ComparatorType;
import tm.server.utils.SessionUtil;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    @NotNull @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @NotNull @Getter
    private final Collection<Endpoint> endpoints = new LinkedHashSet<>();

    @Nullable @Getter @Setter
    private Session currentSession;
    @NotNull @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    @Nullable @Getter
    Connection databaseConnection;

    @Getter
    private ProjectService projectService;
    @Getter
    private TaskService taskService;
    @Getter
    private UserService userService;
    @Getter
    private ServerService serverService;
    @Getter
    private SessionService sessionService;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        try {
            initConnections();
            initServices();
            initCommands(classes);
//            initUsers();
            mainLoop();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void initUsers() throws Throwable {
        final User admin = new User();
        admin.setLogin("admin");
        admin.setPasswordHash(CypherUtil.getMd5("admin"));
        admin.setRole(User.Role.ADMIN);

        ((UserServiceImpl)userService).persist(SessionUtil.getSessionForUser(admin), admin);
        userService.register("demo", "demo");
    }

    private void initConnections() {
        databaseConnection = DatabaseConnectionUtil.getJDBCConnection();
    }

    private void initServices() throws Exception {
        final SqlSessionFactory sessionFactory = DatabaseConnectionUtil.getSessionFactory();
        if (sessionFactory == null) throw new Exception("can not initialize session factory");
        taskService = new TaskServiceImpl(new TaskRepositoryMyBatis(sessionFactory), this);
        projectService = new ProjectServiceImpl(new ProjectRepositoryMyBatis(sessionFactory), this);
        userService = new UserServiceImpl(new UserRepositoryMyBatis(sessionFactory), this);
        serverService = new ServerServiceImpl(this);
        sessionService = new SessionServiceImpl(new SessionRepositoryMyBatis(sessionFactory), this);
    }

    private void initCommands(@Nullable final Class[] classes) {
        if (classes == null) return;
        for (@Nullable final Class clazz : classes) {
            if (clazz == null || !AbstractCommand.class.isAssignableFrom(clazz)) continue;
            try {
                final AbstractCommand instance = (AbstractCommand) clazz.newInstance();
                instance.setServiceLocator(this);
                commands.put(instance.getName(), instance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private void mainLoop() {
        try {
            commands.get("publish-endpoints").execute();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("*** WELCOME TO TASK-MANAGER SERVER ***");
        while (!isTerminated) {
            try {
                final String input = InputHelper.requestLine("enter command:", true);
                if (input == null || input.isEmpty()) continue;

                final Command command = commands.get(input);
                if (command == null) {
                    System.out.println("COMMAND IS NOT SUPPORTED");
                    System.out.println();
                    continue;
                }
                command.execute();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

}
