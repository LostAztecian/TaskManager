package tm.client;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.api.Command;
import tm.client.api.ServiceLocator;
import tm.client.api.entity.PlannedEntity;
import tm.client.api.service.ProjectService;
import tm.client.api.service.TaskService;
import tm.client.api.service.UserService;
import tm.client.command.AbstractCommand;
import tm.client.comparator.ComparatorType;
import tm.client.entity.User;
import tm.client.repository.ProjectRepository;
import tm.client.repository.TaskRepository;
import tm.client.repository.UserRepository;
import tm.client.service.ProjectServiceImpl;
import tm.client.service.TaskServiceImpl;
import tm.client.service.UserServiceImpl;
import tm.client.utils.InputHelper;

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
        System.out.println("*** WELCOME TO TASK-MANAGER CLIENT ***");
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
