package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class TaskRemoveCommand extends Command {

    public static final String NAME = "task-remove";
    private static final String DESCRIPTION = "delete task in a selected project";

    public TaskRemoveCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[TASK DELETE]");
        final Collection<Project> projects = getBootstrap().getProjectService()
                .getByIds(getBootstrap().getCurrentUser().getProjectIds());
        final Collection<String> taskIds = projects.stream()
                .map(Project::getTaskIds)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        final String taskName = InputHelper.requestLine("ENTER TASK NAME:", true);
        if (taskName == null || taskName.isEmpty()) {
            printNoSuchTask();
            return;
        }
        final Collection<Task> tasks = getBootstrap().getTaskService().getByIds(taskIds).stream()
                .filter(t -> t.getName().equals(taskName)).collect(Collectors.toSet());
        if (tasks.isEmpty()) {
            printNoSuchTask();
            return;
        }
        tasks.forEach(t -> {
            getBootstrap().getTaskService().delete(t.getId());
            t.getProject().getTaskIds().remove(t.getId());
        });
        System.out.println("[TASKS REMOVED]");
        System.out.println();
    }

    private void printNoSuchTask() {
        System.out.println("[NO SUCH TASK]");
        System.out.println("[END]");
        System.out.println();
    }

}
