package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class TaskCreateCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-create";
    @NotNull private static final String DESCRIPTION = "save task for a selected project";

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
        System.out.println("[TASK CREATE]");
        final Collection<Project> projects = InputHelper.requestProjectsByName(getServiceLocator());
        if (projects == null || projects.isEmpty()) return;
        final Optional<Project> project = projects.stream().findFirst();
        final String taskName = InputHelper.requestLine("ENTER NAME:", false);
        if (taskName == null) return;

        final String taskDescription = InputHelper.requestLine("ENTER DESCRIPTION:", true);

        Date taskStartDate = InputHelper.requestDate("START");
        if (taskStartDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskStartDate = new Date();
        }

        Date taskEndDate = InputHelper.requestDate("END");
        if (taskEndDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskEndDate = new Date();
        }

        final Task task = new Task(getServiceLocator().getCurrentUser().getUserId(), taskName);
        task.setProjectId(project.get().getId());
        task.setDescription(taskDescription);
        task.setStartDate(taskStartDate);
        task.setEndDate(taskEndDate);
        getServiceLocator().getTaskService().save(task);
        System.out.printf("[TASK \'%s\' CREATED] %n%n", task.getName());
    }

}
