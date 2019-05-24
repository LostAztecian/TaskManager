package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;

public class TaskClearCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-clear";
    @NotNull private static final String DESCRIPTION = "removes all tasks for current user";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws IOException {
        getServiceLocator().getTaskService().deleteAll();
        System.out.printf("[ALL TASKS FOR USER \'%s\' REMOVED] %n%n", getServiceLocator().getCurrentUser().getLogin());
    }

}
