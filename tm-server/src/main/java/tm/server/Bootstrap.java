package tm.server;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.entity.Session;
import tm.common.entity.User;
import tm.server.api.ServiceLocator;
import tm.common.api.entity.PlannedEntity;
import tm.server.api.service.ProjectService;
import tm.server.api.service.ServerService;
import tm.server.api.service.TaskService;
import tm.server.api.service.UserService;
import tm.server.command.AbstractCommand;
import tm.server.service.ServerServiceImpl;
import tm.server.utils.CypherUtil;
import tm.server.utils.InputHelper;
import tm.common.comparator.ComparatorType;
import tm.server.repository.ProjectRepository;
import tm.server.repository.TaskRepository;
import tm.server.repository.UserRepository;
import tm.server.service.ProjectServiceImpl;
import tm.server.service.TaskServiceImpl;
import tm.server.service.UserServiceImpl;
import tm.server.utils.SessionUtil;

import javax.xml.ws.Endpoint;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @Getter
    private final Collection<Endpoint> endpoints = new LinkedHashSet<>();

    @Getter @Setter
    private Session currentSession;
    @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    @Getter
    private ProjectService projectService;
    @Getter
    private TaskService taskService;
    @Getter
    private UserService userService;
    @Getter
    private ServerService serverService;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        initServices();
        if (classes != null) initCommands(classes);
        initUsers();
        mainLoop();
    }

    private void initUsers() {
        final User admin = new User();
        admin.setLogin("admin");
        admin.setPasswordHash(CypherUtil.getMd5("admin"));
        admin.setRole(User.Role.ADMIN);

        ((UserServiceImpl)userService).persist(SessionUtil.getSessionForUser(admin), admin);
        userService.register("demo", "demo");
    }

    private void initServices() {
        taskService = new TaskServiceImpl(new TaskRepository(), this);
        projectService = new ProjectServiceImpl(new ProjectRepository(), this);
        userService = new UserServiceImpl((new UserRepository()), this);
        serverService = new ServerServiceImpl(this);
    }

    private void initCommands(@NotNull final Class[] classes) {
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
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
