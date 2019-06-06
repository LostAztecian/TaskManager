package tm.server.command.task;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.common.entity.Task;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

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
    protected void run() throws Throwable {
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[TASK SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<Task> searchResult = getServiceLocator().getTaskService()
                .search(getServiceLocator().getCurrentSession(), searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        final Collection<Task> sortedTasks = new TreeSet<>(session.getSortMethod().comparator);
        sortedTasks.addAll(searchResult);
        System.out.println("TASKS MATCHING CRITERIA:");
        int index = 1;
        for (final Task task : sortedTasks) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }
}
