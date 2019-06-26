package tm.client.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tm.client.api.Command;
import tm.client.api.PlannedEntity;
import tm.client.api.ServiceLocator;
import tm.client.command.AbstractCommand;
import tm.client.comparator.ComparatorType;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ClientBootstrap implements ServiceLocator {

    @Getter @Setter
    private SessionDTO currentSession = null;

    @Getter
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean isTerminated = false;

    @Getter @Autowired
    private UserService userService;
    @Getter @Autowired
    private ProjectService projectService;
    @Getter @Autowired
    private TaskService taskService;
    @Getter @Autowired
    private ServerService serverService;

    @Getter @Setter
    private Comparator<PlannedEntity> currentSortMethod = ComparatorType.BY_CREATION_DATE.comparator;

    public void terminate() { isTerminated = true; }

    public void init(@Nullable final Class[] classes) {
        if (classes != null) initCommands(classes);
//        initServices();
        mainLoop();
    }

//    private void initServices() {
//        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
//        userService = new UserWebServiceBeanService().getUserWebServiceBeanPort();
//        projectService = new ProjectWebServiceBeanService().getProjectWebServiceBeanPort();
//        taskService = new TaskWebServiceBeanService().getTaskWebServiceBeanPort();
//        serverService = new ServerWebServiceBeanService().getServerWebServiceBeanPort();
//    }

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
