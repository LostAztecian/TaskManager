package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;

public class TaskRemoveCommand extends Command {

    public static final String NAME = "task-remove";
    private static final String DESCRIPTION = "delete task in a selected project";

    public TaskRemoveCommand(final Bootstrap bootstrap) {
        super(bootstrap, NAME, DESCRIPTION);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("[TASK DELETE]");
        final Project taskProject = requestProject();
        if (taskProject == null) return;

        final String input = InputHelper.requestLine("ENTER NAME:", true);
        if (input == null || input.isEmpty()) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }
        Collection<Task> tasksWithName = getBootstrap().getTaskService().getAllByName(input);
        tasksWithName.retainAll(getBootstrap().getTaskService().getByIds(taskProject.getTaskIds()));
        if (tasksWithName.isEmpty()) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }

        tasksWithName.forEach(t -> taskProject.getTaskIds().removeIf(id -> id.equals(t.getId())));
        System.out.printf("[TASK %s DELETED] %n%n", input.toUpperCase());
    }

}
