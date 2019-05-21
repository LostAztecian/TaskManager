package ru.stoliarenkoas.tm.command;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TaskClearCommand extends AbstractCommand {

    public static final String NAME = "task-clear";
    private static final String DESCRIPTION = "removes all tasks for current user";

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
        getServiceLocator().getProjectService()
            .getAllByParentId(getServiceLocator().getCurrentUser().getId())
            .forEach(p ->
                getServiceLocator().getTaskService().getAllByParentId(p.getId())
                        .forEach(getServiceLocator().getTaskService()::delete));
        System.out.printf("[ALL TASKS FOR USER \'%s\' REMOVED] %n%n", getServiceLocator().getCurrentUser().getLogin());
    }

}
