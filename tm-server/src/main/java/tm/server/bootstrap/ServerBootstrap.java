package tm.server.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.api.entity.PlannedEntity;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.server.annotations.Jpa;
import tm.server.api.ServiceLocator;
import tm.server.api.service.*;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.util.*;

@ApplicationScoped
@NoArgsConstructor
public class ServerBootstrap implements ServiceLocator {

    @NotNull @Getter
    private Map<String, Command> commands = new LinkedHashMap<>();

    @NotNull @Getter
    private Collection<Endpoint> endpoints = new LinkedHashSet<>();

    private boolean isTerminated = false;

    @Nullable @Getter @Setter
    private SessionDTO currentSession = null;

    @NotNull @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    @Nullable @Getter
    private Connection databaseConnection = null;

    @Getter @Inject @Jpa
    private ProjectService projectService;
    @Getter @Inject @Jpa
    private TaskService taskService;
    @Getter @Inject @Jpa
    private UserService userService;
    @Getter @Inject @Jpa
    private SessionService sessionService;
    @Getter @Inject
    private ServerService serverService;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        try {
            initCommands(classes);
//            initUsers();
            mainLoop();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void initUsers() throws Throwable {
        userService.register("admin", "admin");
        userService.register("demo", "demo");
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