package ru.stoliarenkoas.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Command;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;
import ru.stoliarenkoas.tm.repository.ProjectMapRepository;
import ru.stoliarenkoas.tm.repository.TaskMapRepository;
import ru.stoliarenkoas.tm.repository.UserMapRepository;
import ru.stoliarenkoas.tm.service.ProjectServiceImpl;
import ru.stoliarenkoas.tm.service.TaskServiceImpl;
import ru.stoliarenkoas.tm.service.UserServiceImpl;

import java.io.IOException;
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
    private Service<Project> projectService;
    @Getter
    private Service<Task> taskService;
    @Getter
    private Service<User> userService;

    public void terminate() { isTerminated = true; }

    public void init(final @Nullable Class[] classes) {
        initMethods();
        if (classes != null) initCommands(classes);
        initUsers();
        mainLoop();
    }

    private void initUsers() {
        userService.save(new User("admin", "admin", User.Role.ADMIN));
        userService.save(new User("demo", "demo", User.Role.USER));
    }

    private void initMethods() {
        taskService = new TaskServiceImpl(new TaskMapRepository(), this);
        projectService = new ProjectServiceImpl(new ProjectMapRepository(), this);
        userService = new UserServiceImpl((new UserMapRepository()), this);
    }

    private void initCommands(final @NotNull Class[] classes) {
        for (final @Nullable Class clazz : classes) {
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
        System.out.println("*** WELCOME TO TASK-MANAGER ***");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
