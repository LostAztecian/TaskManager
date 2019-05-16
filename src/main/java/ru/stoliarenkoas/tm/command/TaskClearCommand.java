package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;

public class TaskClearCommand extends Command {

    public static final String NAME = "task-clear";
    private static final String DESCRIPTION = "removes all tasks from a selected project";

    public TaskClearCommand(final Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void execute() throws IOException {
        final Project taskProject = requestProject();
        if (taskProject == null) return;
        getBootstrap().getTaskService().deleteByIds(taskProject.getTaskIds());
        System.out.printf("[ALL PROJECT %s TASKS REMOVED] %n%n", taskProject.getName());
    }

}
