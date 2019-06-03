package tm.client.command.task;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.Session;
import tm.common.api.webservice.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public class TaskSearchCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-search";
    @NotNull public static final String DESCRIPTION = "search task by name or description";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    protected void run() throws IOException {
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[TASK SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<Task> searchResult = getServiceLocator().getTaskService().searchTask(session, searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        System.out.println("TASKS MATCHING CRITERIA:");
        int index = 1;
        for (final Task task : searchResult) {
            System.out.println(index++ + " " + task.getName() + ": " + task.getDescription());
            System.out.println("\tBelongs to project: " + task.getProjectId() + " user: " + task.getUserId());
            System.out.println("\tCreated: " + task.getCreationDate() + " Start: " + task.getStartDate() + " End: " + task.getEndDate());
        }
        System.out.println();
    }
}
