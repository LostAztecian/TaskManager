package tm.client.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tm.common.api.webservice.ProjectService;
import tm.common.api.webservice.ServerService;
import tm.common.api.webservice.TaskService;
import tm.common.api.webservice.UserService;
import tm.server.webservice.ProjectWebServiceBeanService;
import tm.server.webservice.ServerWebServiceBeanService;
import tm.server.webservice.TaskWebServiceBeanService;
import tm.server.webservice.UserWebServiceBeanService;

@Configuration
@ComponentScan(basePackages = "tm.client")
public class Producers {

    @Bean
    public static UserService getUserService() {
        return new UserWebServiceBeanService().getUserWebServiceBeanPort();
    }

    @Bean
    public static ServerService getServerService() {
        return new ServerWebServiceBeanService().getServerWebServiceBeanPort();
    }

    @Bean
    public static ProjectService getProjectService() {
        return new ProjectWebServiceBeanService().getProjectWebServiceBeanPort();
    }

    @Bean
    public static TaskService getTaskService() {
        return new TaskWebServiceBeanService().getTaskWebServiceBeanPort();
    }

}
