package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class TaskListCommand extends Command {

    public static final String NAME = "task-list";
    private static final String DESCRIPTION = "show all tasks for all projects of a user";

    public TaskListCommand(final Bootstrap bootstrap) {
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
        final Collection<Task> allTasks = getBootstrap().getTaskService().getByIds(taskIds);
        if (allTasks.isEmpty()) {
            System.out.println("[TASK LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("TASK LIST");
        int index = 1;
        for (final Task task : allTasks) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }

}
