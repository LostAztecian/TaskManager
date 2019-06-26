package tm.server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tm.server.api.ServiceLocator;
import tm.server.bootstrap.ServerBootstrap;
import tm.server.command.general.*;
import tm.server.command.persist.bin.DataClearBinary;
import tm.server.command.persist.bin.DataLoadBinary;
import tm.server.command.persist.bin.DataSaveBinary;
import tm.server.command.persist.fasterxml.*;
import tm.server.command.persist.jaxb.*;
import tm.server.command.project.*;
import tm.server.command.task.*;
import tm.server.command.user.*;

public class ServerApplication {

    private static final Class[] CLASSES = {
            HelpCommand.class, AboutCommand.class, PublishEndpoints.class,
            ExitCommand.class, SetSortMethodCommand.class,

            UserLoginCommand.class, UserLogoutCommand.class,
            UserShowProfileCommand.class, UserChangePasswordCommand.class,
            UserRegisterCommand.class,

            ProjectCreateCommand.class, ProjectRemoveCommand.class,
            ProjectListCommand.class, ProjectTaskListCommand.class,
            ProjectClearCommand.class, ProjectSearchCommand.class,

            TaskCreateCommand.class, TaskRemoveCommand.class,
            TaskListCommand.class, TaskClearCommand.class,
            TaskSearchCommand.class,

            DataSaveBinary.class, DataLoadBinary.class, DataClearBinary.class,
            DataSaveJaxbXml.class, DataLoadJaxbXml.class, DataClearJaxbXml.class,
            DataSaveJaxbJson.class, DataLoadJaxbJson.class, DataClearJaxbJson.class,
            DataSaveFasterXmlXml.class, DataLoadFasterXmlXml.class, DataClearFasterXmlXml.class,
            DataSaveFasterXmlJson.class, DataLoadFasterXmlJson.class, DataClearFasterXmlJson.class
    };

    public static void main(String[] args) {

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tm.server", "tm.server.repository");
        ServiceLocator serviceLocator = context.getBean("bootstrap", ServerBootstrap.class);
        serviceLocator.init(CLASSES);

        hazelcastInstance.shutdown();

    }

}
