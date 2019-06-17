package tm.client.utils;

import tm.common.api.webservice.ProjectService;
import tm.common.api.webservice.ServerService;
import tm.common.api.webservice.TaskService;
import tm.common.api.webservice.UserService;
import tm.server.webservice.ProjectWebServiceBeanService;
import tm.server.webservice.ServerWebServiceBeanService;
import tm.server.webservice.TaskWebServiceBeanService;
import tm.server.webservice.UserWebServiceBeanService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class Producers {

    @ApplicationScoped @Produces
    public static UserService getUserService() {
        return new UserWebServiceBeanService().getUserWebServiceBeanPort();
    }

    @ApplicationScoped @Produces
    public static ServerService getServerService() {
        return new ServerWebServiceBeanService().getServerWebServiceBeanPort();
    }

    @ApplicationScoped @Produces
    public static ProjectService getProjectService() {
        return new ProjectWebServiceBeanService().getProjectWebServiceBeanPort();
    }

    @ApplicationScoped @Produces
    public static TaskService getTaskService() {
        return new TaskWebServiceBeanService().getTaskWebServiceBeanPort();
    }

}
