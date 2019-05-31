package tm.client.command.task;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.entity.Session;

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[TASK DELETE]");

        final String taskName = InputHelper.requestLine("ENTER TASK NAME:", true);
        if (taskName == null || taskName.isEmpty()) {
            printNoSuchTask();
            return;
        }
        final Boolean success = getServiceLocator().getTaskService().deleteTasksByName(session, taskName);
        if (!success) {
            printNoSuchTask();
            return;
        }
        System.out.println("[TASKS REMOVED]");
        System.out.println();
    }

    private void printNoSuchTask() {
        System.out.println("[NO SUCH TASK]");
        System.out.println("[END]");
        System.out.println();
    }

}
