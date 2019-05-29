package tm.server.command.general;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.webservice.ProjectWebServiceBean;
import tm.server.webservice.TaskWebServiceBean;
import tm.server.webservice.UserWebServiceBean;

import javax.xml.ws.Endpoint;

public class Test extends AbstractCommand {

    @NotNull public static final String NAME = "publish-endpoints";
    @NotNull private static final String DESCRIPTION = "publish all endpoints";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    protected void run() throws Exception {
        final Endpoint userEndpoint = Endpoint.create(new UserWebServiceBean(getServiceLocator().getUserService()));
        final String userServiceURL = "http://localhost:8080/userService";
        userEndpoint.publish(userServiceURL);
        getServiceLocator().getEndpoints().add(userEndpoint);

        final Endpoint projectEndpoint = Endpoint.create(new ProjectWebServiceBean(getServiceLocator().getProjectService()));
        final String projectServiceURL = "http://localhost:8080/projectService";
        projectEndpoint.publish(projectServiceURL);
        getServiceLocator().getEndpoints().add(projectEndpoint);

        final Endpoint taskEndpoint = Endpoint.create(new TaskWebServiceBean(getServiceLocator().getTaskService()));
        final String taskServiceURL = "http://localhost:8080/taskService";
        taskEndpoint.publish(taskServiceURL);
        getServiceLocator().getEndpoints().add(taskEndpoint);
        System.out.println("[ENDPOINTS PUBLISHED]");
    }

}
