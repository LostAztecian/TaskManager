package tm.server.command.task;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;
import tm.server.command.AbstractCommand;

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
    public void run() throws Throwable {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        getServiceLocator().getTaskService().deleteAll(session);
        System.out.printf("[ALL TASKS FOR USER \'%s\' REMOVED] %n%n", session.getUserId());
    }

}
