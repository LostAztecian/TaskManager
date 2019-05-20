package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class TaskClearCommand extends AbstractCommand {

    public static final String NAME = "task-clear";
    private static final String DESCRIPTION = "removes all tasks for current user";

    public TaskClearCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

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
