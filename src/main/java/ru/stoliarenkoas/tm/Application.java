package ru.stoliarenkoas.tm;

import ru.stoliarenkoas.tm.command.general.AboutCommand;
import ru.stoliarenkoas.tm.command.general.ExitCommand;
import ru.stoliarenkoas.tm.command.general.HelpCommand;
import ru.stoliarenkoas.tm.command.general.SetSortMethodCommand;
import ru.stoliarenkoas.tm.command.project.*;
import ru.stoliarenkoas.tm.command.task.*;
import ru.stoliarenkoas.tm.command.user.*;

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
            TaskSearchCommand.class
    };

    public static void main(String[] args) {
        new Bootstrap().init(CLASSES);
    }

}
