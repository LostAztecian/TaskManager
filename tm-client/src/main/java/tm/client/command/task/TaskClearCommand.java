package tm.client.command.task;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Session;

import java.io.IOException;

public class TaskClearCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-clear";
    @NotNull private static final String DESCRIPTION = "removes all tasks for current user";

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
        final Boolean success = getServiceLocator().getTaskService().deleteAllTasks(session);
        if (!success) {
            System.out.println("[TASK REMOVAL FAILURE]");
            return;
        }
        System.out.printf("[ALL TASKS FOR USER \'%s\' REMOVED] %n%n", session.getUserLogin());
    }

}
