package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class TaskCreateCommand extends Command {

    public static final String NAME = "task-create";
    private static final String DESCRIPTION = "create task for a selected project";

    public TaskCreateCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[TASK CREATE]");
        final Collection<Project> projects = requestProjectsByName();
        final Optional<Project> project = projects.stream().findFirst();
        if (project.isPresent()) {
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

            final Task task = new Task(project.get(), taskName, taskDescription, taskStartDate);
            getBootstrap().getTaskService().save(task);
            project.get().getTaskIds().add(task.getId());
            System.out.printf("[TASK %s CREATED] %n%n", task.getName().toUpperCase());
        }
    }

}