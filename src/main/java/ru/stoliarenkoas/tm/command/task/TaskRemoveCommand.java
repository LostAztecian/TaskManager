package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.utils.InputHelper;

import java.io.IOException;

public class TaskRemoveCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-remove";
    @NotNull private static final String DESCRIPTION = "delete task in a selected project";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws IOException {
        System.out.println("[TASK DELETE]");

        final String taskName = InputHelper.requestLine("ENTER TASK NAME:", true);
        if (taskName == null || taskName.isEmpty()) {
            printNoSuchTask();
            return;
        }
        getServiceLocator().getTaskService().deleteByName(taskName);
        System.out.println("[TASKS REMOVED]");
        System.out.println();
    }

    private void printNoSuchTask() {
        System.out.println("[NO SUCH TASK]");
        System.out.println("[END]");
        System.out.println();
    }

}
