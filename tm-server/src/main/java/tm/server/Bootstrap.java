package tm.server;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.Command;
import tm.server.api.ServiceLocator;
import tm.server.api.entity.PlannedEntity;
import tm.server.api.service.ProjectService;
import tm.server.api.service.TaskService;
import tm.server.api.service.UserService;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;
import tm.server.entity.User;
import tm.server.comparator.ComparatorType;
import tm.server.repository.ProjectRepository;
import tm.server.repository.TaskRepository;
import tm.server.repository.UserRepository;
import tm.server.service.ProjectServiceImpl;
import tm.server.service.TaskServiceImpl;
import tm.server.service.UserServiceImpl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {

    @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @Getter @Setter
    private User currentUser;
    @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    @Getter
    private ProjectService projectService;
    @Getter
    private TaskService taskService;
    @Getter
    private UserService userService;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        initMethods();
        if (classes != null) initCommands(classes);
        initUsers();
        mainLoop();
    }

    private void initUsers() {
        userService.persist(new User("admin", "admin", User.Role.ADMIN));
        userService.persist(new User("demo", "demo", User.Role.USER));
    }

    private void initMethods() {
        taskService = new TaskServiceImpl(new TaskRepository(), this);
        projectService = new ProjectServiceImpl(new ProjectRepository(), this);
        userService = new UserServiceImpl((new UserRepository()), this);
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
        System.out.println("*** WELCOME TO TASK-MANAGER SERVER ***");
        while (!isTerminated) {
            try {
                final String input = InputHelper.requestLine("enter command", true);
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
