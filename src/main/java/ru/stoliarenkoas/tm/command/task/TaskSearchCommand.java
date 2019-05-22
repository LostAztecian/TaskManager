package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;

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
        System.out.println("[TASK SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<Task> searchResult = getServiceLocator().getTaskService().search(searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        System.out.println("TASKS MATCHING CRITERIA:");
        int index = 1;
        for (final Task task : searchResult) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }
}