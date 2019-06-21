package tm.server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
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

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

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

        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();
//        ContextControl contextControl = container.getContextControl();
//        contextControl.startContext(ApplicationScoped.class);

        SeContainerInitializer.newInstance().initialize();
        CDI.current().select(ServerBootstrap.class).get().init(CLASSES);

        container.shutdown();
        hazelcastInstance.shutdown();

    }

}
