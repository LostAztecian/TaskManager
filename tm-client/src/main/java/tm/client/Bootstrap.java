package tm.client;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.api.ServiceLocator;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.client.webservice.ProjectServiceClient;
import tm.client.webservice.ServerWebServiceClient;
import tm.client.webservice.TaskServiceClient;
import tm.client.webservice.UserServiceClient;
import tm.common.api.Command;
import tm.common.api.entity.PlannedEntity;
import tm.common.api.webservice.ProjectWebService;
import tm.common.api.webservice.ServerWebService;
import tm.common.api.webservice.TaskWebService;
import tm.common.api.webservice.UserWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.User;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {

    @Getter @Setter
    private User currentUser = null;

    @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @Getter
    private UserWebService userService;
    @Getter
    private ProjectWebService projectService;
    @Getter
    private TaskWebService taskService;
    @Getter
    private ServerWebService serverService;

    @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        if (classes != null) initCommands(classes);
        initServices();
        mainLoop();
    }

    private void initServices() {
        userService = new UserServiceClient();
        projectService = new ProjectServiceClient();
        taskService = new TaskServiceClient();
        serverService = new ServerWebServiceClient();
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
