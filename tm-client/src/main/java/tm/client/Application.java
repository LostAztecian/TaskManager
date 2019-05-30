package tm.client;

import tm.client.command.general.*;
import tm.client.command.persist.bin.DataClearBinary;
import tm.client.command.persist.bin.DataLoadBinary;
import tm.client.command.persist.bin.DataSaveBinary;
import tm.client.command.persist.fasterxml.*;
import tm.client.command.persist.jaxb.*;
import tm.client.command.project.*;
import tm.client.command.task.*;
import tm.client.command.user.*;

public class Application {

    private static final Class[] CLASSES = {
            HelpCommand.class, AboutCommand.class,
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
        new Bootstrap().init(CLASSES);
    }

}
