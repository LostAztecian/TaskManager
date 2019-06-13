package tm.server.command.task;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.command.AbstractCommand;

import java.util.Collection;

public class TaskListCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-list";
    @NotNull private static final String DESCRIPTION = "show all tasks for all projects of a user";

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
        final Collection<TaskDTO> allTasks = getServiceLocator().getTaskService().getAll(session);
        if (allTasks.isEmpty()) {
            System.out.println("[TASK LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("TASK LIST");
        int index = 1;
        for (final TaskDTO task : allTasks) {
            System.out.println(index++ + " " + task.getName() + ": " + task.getDescription());
            System.out.println("\tBelongs to project: " + task.getProjectId() + " user: " + task.getUserId());
            System.out.println("\tCreated: " + task.getCreationDate() + " Start: " + task.getStartDate() + " End: " + task.getEndDate());
        }
        System.out.println();
    }

}
