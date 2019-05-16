package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;

public class ProjectTaskListCommand extends Command {

    public static final String NAME = "project-task-list";
    private static final String DESCRIPTION = "show all tasks for selected project";

    public ProjectTaskListCommand(final Bootstrap bootstrap) {
        super(bootstrap, NAME, DESCRIPTION);
    }

    @Override
    public void execute() throws IOException {
        final Project project = requestProject();
        if (project == null) return;
        final Collection<Task> projectTasks = getBootstrap().getTaskService().getByIds(project.getTaskIds());
        if (projectTasks.isEmpty()) {
            System.out.println("[PROJECT HAS NO TASKS]");
            System.out.println();
            return;
        }
        projectTasks.forEach(System.out::println);
    }

}
