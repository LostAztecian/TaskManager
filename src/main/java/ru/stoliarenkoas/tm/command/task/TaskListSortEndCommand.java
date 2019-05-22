package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.comparator.task.TaskCreationComparator;
import ru.stoliarenkoas.tm.entity.comparator.task.TaskEndComparator;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public class TaskListSortEndCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-list-sort-end";
    @NotNull private static final String DESCRIPTION = "show all tasks for all projects of a user sorted by end date";

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
        final Collection<Project> projects = getServiceLocator().getProjectService()
                .getAllByParentId(getServiceLocator().getCurrentUser().getId()); //method can be invoked only when user != null
        final Collection<Task> allTasks = new TreeSet<>(new TaskEndComparator());
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
