package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class TaskListCommand extends AbstractCommand {

    public static final String NAME = "task-list";
    private static final String DESCRIPTION = "show all tasks for all projects of a user";

    public TaskListCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        final Collection<Project> projects = getServiceLocator().getProjectService()
                .getAllByParentId(getServiceLocator().getCurrentUser().getId());
        final Collection<Task> allTasks = new LinkedHashSet<>();
        projects.forEach(p -> allTasks.addAll(getServiceLocator().getTaskService().getAllByParentId(p.getId())));
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
