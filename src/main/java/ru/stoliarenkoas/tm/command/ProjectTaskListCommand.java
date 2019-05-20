package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

public class ProjectTaskListCommand extends AbstractCommand {

    public static final String NAME = "project-task-list";
    private static final String DESCRIPTION = "show all tasks for selected project";

    public ProjectTaskListCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        final Collection<Project> projects = requestProjectsByName();
        if (projects == null) return;
        final Collection<Task> tasks = new LinkedHashSet<>();
        for (final Project project : projects) {
            tasks.addAll(getServiceLocator().getTaskService().getByIds(project.getTaskIds()));
        }
        if (tasks.isEmpty()) {
            System.out.println("[PROJECT HAS NO TASKS]");
            System.out.println();
            return;
        }
        int index = 1;
        for (final Task task : tasks) {
            System.out.printf("%d. %s.%n", index, task);
        }
    }

}
