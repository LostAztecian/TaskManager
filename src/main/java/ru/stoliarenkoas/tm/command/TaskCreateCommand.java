package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class TaskCreateCommand extends AbstractCommand {

    public static final String NAME = "task-create";
    private static final String DESCRIPTION = "save task for a selected project";

    public TaskCreateCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[TASK CREATE]");
        final Collection<Project> projects = requestProjectsByName();
        if (projects == null || projects.isEmpty()) return;
        final Optional<Project> project = projects.stream().findFirst();
        final String taskName = InputHelper.requestLine("ENTER NAME:", false);
        if (taskName == null) return;

        final String taskDescription = InputHelper.requestLine("ENTER DESCRIPTION:", true);

        Date taskStartDate;
        try {
            taskStartDate = InputHelper.requestDate();
        } catch (IOException e) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskStartDate = new Date();
        }

        final Task task = new Task(project.get().getId(), taskName, taskDescription, taskStartDate);
        getServiceLocator().getTaskService().save(task);
        System.out.printf("[TASK %s CREATED] %n%n", task.getName().toUpperCase());
    }

}
