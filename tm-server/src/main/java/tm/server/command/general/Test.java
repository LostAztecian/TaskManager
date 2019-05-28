package tm.server.command.general;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.webservice.ProjectWebServiceBean;
import tm.server.webservice.TaskWebServiceBean;
import tm.server.webservice.UserWebServiceBean;

import javax.xml.ws.Endpoint;

public class Test extends AbstractCommand {

    @NotNull public static final String NAME = "test";
    @NotNull private static final String DESCRIPTION = "test";

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
        final String userServiceURL = "http://localhost:8080/userService";
        Endpoint.publish(userServiceURL, new UserWebServiceBean());
        final String projectServiceURL = "http://localhost:8080/projectService";
        Endpoint.publish(projectServiceURL, new ProjectWebServiceBean());
        final String taskServiceURL = "http://localhost:8080/taskService";
        Endpoint.publish(taskServiceURL, new TaskWebServiceBean());

    }

}
