package ru.stoliarenkoas.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.service.TaskService;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public class ProjectTaskListCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-task-list";
    @NotNull private static final String DESCRIPTION = "show all tasks for selected project";

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
        final Collection<Project> projects = InputHelper.requestProjectsByName(getServiceLocator());
        if (projects == null) return;
        final Collection<Task> tasks = new TreeSet<>(getServiceLocator().getCurrentSortMethod());
        for (final Project project : projects) {
            tasks.addAll(((TaskService)(getServiceLocator().getTaskService())).getTasksByProjectId(project.getId()));
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
