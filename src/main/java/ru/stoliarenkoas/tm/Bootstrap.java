package ru.stoliarenkoas.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Command;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.command.*;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.User;
import ru.stoliarenkoas.tm.repository.ProjectMapRepository;
import ru.stoliarenkoas.tm.repository.TaskMapRepository;
import ru.stoliarenkoas.tm.repository.UserMapRepository;
import ru.stoliarenkoas.tm.service.ProjectServiceImpl;
import ru.stoliarenkoas.tm.service.TaskServiceImpl;
import ru.stoliarenkoas.tm.service.UserServiceImpl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {

    @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @Getter @Setter
    private User currentUser;

    @Getter
    private ProjectServiceImpl projectService;
    @Getter
    private TaskServiceImpl taskService;
    @Getter
    private UserServiceImpl userService;

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
        taskService = new TaskServiceImpl(new TaskMapRepository());
        projectService = new ProjectServiceImpl(new ProjectMapRepository(), taskService);
        userService = new UserServiceImpl((new UserMapRepository()), projectService);
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
        try {
            while (!isTerminated) {
                final String input = InputHelper.requestLine("enter command", true);
                if (input == null || input.isEmpty()) continue;

                final Command command = commands.get(input);
                if (command == null) {
                    System.out.println("COMMAND IS NOT SUPPORTED");
                    System.out.println();
                    continue;
                }
                command.execute();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
