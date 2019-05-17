package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class TaskClearCommand extends Command {

    public static final String NAME = "task-clear";
    private static final String DESCRIPTION = "removes all tasks for current user";

    public TaskClearCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        final Collection<Project> projects = getBootstrap().getProjectService()
                .getByIds(getBootstrap().getCurrentUser().getProjectIds());
        final Collection<String> taskIds = projects.stream()
                .map(Project::getTaskIds)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        getBootstrap().getTaskService().deleteByIds(taskIds);
        projects.forEach(p -> p.getTaskIds().clear());
        System.out.printf("[ALL TASKS FOR USER \'%s\' REMOVED] %n%n", getBootstrap().getCurrentUser().getLogin());
    }

}
