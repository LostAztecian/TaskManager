package ru.stoliarenkoas.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class TaskRemoveCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-remove";
    @NotNull private static final String DESCRIPTION = "delete task in a selected project";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws IOException {
        System.out.println("[TASK DELETE]");
        final Collection<String> projectsIds = getServiceLocator().getProjectService()
                .getAllByParentId(getServiceLocator().getCurrentUser().getId()) //method can be invoked only when user != null
                .stream().map(Project::getId).collect(Collectors.toSet());

        final String taskName = InputHelper.requestLine("ENTER TASK NAME:", true);
        if (taskName == null || taskName.isEmpty()) {
            printNoSuchTask();
            return;
        }
        final Collection<Task> tasks = getServiceLocator().getTaskService().getAllByName(taskName)
                .stream().filter(t -> projectsIds.contains(t.getParentId())).collect(Collectors.toSet());
        if (tasks.isEmpty()) {
            printNoSuchTask();
            return;
        }
        tasks.forEach(t -> getServiceLocator().getTaskService().delete(t.getId()));
        System.out.println("[TASKS REMOVED]");
        System.out.println();
    }

    private void printNoSuchTask() {
        System.out.println("[NO SUCH TASK]");
        System.out.println("[END]");
        System.out.println();
    }

}
