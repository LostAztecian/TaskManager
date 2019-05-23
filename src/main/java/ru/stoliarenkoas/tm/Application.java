package ru.stoliarenkoas.tm;

import ru.stoliarenkoas.tm.command.general.*;
import ru.stoliarenkoas.tm.command.persist.bin.DataClearBinary;
import ru.stoliarenkoas.tm.command.persist.bin.DataLoadBinary;
import ru.stoliarenkoas.tm.command.persist.bin.DataSaveBinary;
import ru.stoliarenkoas.tm.command.persist.fasterxml.*;
import ru.stoliarenkoas.tm.command.persist.jaxb.*;
import ru.stoliarenkoas.tm.command.project.*;
import ru.stoliarenkoas.tm.command.task.*;
import ru.stoliarenkoas.tm.command.user.*;

public class Application {

    private static final Class[] CLASSES = {
            HelpCommand.class, AboutCommand.class, Test.class,
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
