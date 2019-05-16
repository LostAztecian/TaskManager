package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;

public class TaskListCommand extends Command {

    public static final String NAME = "task-list";
    private static final String DESCRIPTION = "show all tasks for all projects";

    public TaskListCommand(final Bootstrap bootstrap) {
        super(bootstrap, NAME, DESCRIPTION);
    }

    @Override
    public void execute() throws IOException {
        final Collection<Task> allTasks = getBootstrap().getTaskService().getAll();
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
