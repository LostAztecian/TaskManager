package tm.server;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;
import tm.server.api.ServiceLocator;
import tm.common.api.entity.PlannedEntity;
import tm.server.api.service.*;
import tm.server.command.AbstractCommand;
import tm.server.repository.hibernate.ProjectRepositoryHibernate;
import tm.server.repository.mybatis.ProjectRepositoryMyBatis;
import tm.server.repository.mybatis.SessionRepositoryMyBatis;
import tm.server.repository.mybatis.TaskRepositoryMyBatis;
import tm.server.repository.mybatis.UserRepositoryMyBatis;
import tm.server.service.*;
import tm.server.service.jpa.ProjectServiceJPA;
import tm.server.service.jpa.SessionServiceJPA;
import tm.server.service.jpa.TaskServiceJPA;
import tm.server.service.jpa.UserServiceJPA;
import tm.server.utils.CypherUtil;
import tm.server.utils.DatabaseUtil;
import tm.server.utils.InputHelper;
import tm.common.comparator.ComparatorType;
import tm.server.utils.SessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    private SessionDTO currentSession;
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
            initServices();
            initCommands(classes);
//            initUsers();
            mainLoop();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void initUsers() throws Throwable {
        final UserDTO admin = new UserDTO();
        admin.setLogin("admin");
        admin.setPasswordHash(CypherUtil.getMd5("admin"));
        admin.setRole(UserDTO.Role.ADMIN);

        ((UserServiceImpl)userService).persist(SessionUtil.getSessionForUser(admin), admin);
        userService.register("demo", "demo");
    }

    private void initJdbcConnections() throws Exception {
        databaseConnection = DatabaseUtil.getJDBCConnection();
        if (databaseConnection == null) throw new NullPointerException("database connection is null");
        databaseConnection.setAutoCommit(false);
    }

    private SqlSession initMyBatisConnections() throws Exception {
        final SqlSessionFactory sessionFactory = DatabaseUtil.getSessionFactory();
        final SqlSession sqlSession = sessionFactory.openSession();
        databaseConnection = sqlSession.getConnection();
        databaseConnection.setAutoCommit(false);
        return sqlSession;
    }

    private EntityManager initHibernateConnection() {
        final EntityManagerFactory factory = DatabaseUtil.getEntityManagerFactory();
        final EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }

    private void initServices() throws Exception {
//        final SqlSession sqlSession = initMyBatisConnections();
        final EntityManagerFactory factory = DatabaseUtil.getEntityManagerFactory();
        taskService = new TaskServiceJPA(factory, this);
        projectService = new ProjectServiceJPA(factory, this);
        userService = new UserServiceJPA(factory, this);
        serverService = new ServerServiceImpl(this);
        sessionService = new SessionServiceJPA(factory, this);
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
