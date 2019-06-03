package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.Project;
import tm.common.api.webservice.Session;
import tm.common.api.webservice.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ProjectTaskListCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-task-list";
    @NotNull private static final String DESCRIPTION = "show all tasks for selected project";

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
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) return;
        final Collection<Project> projects = getServiceLocator().getProjectService().getProjectsByName(session, projectName);
        final Collection<Task> tasks = new ArrayList<>();
        for (final Project project : projects) {
            tasks.addAll(getServiceLocator().getTaskService().getTasksByProjectId(session, project.getId()));
        }
        if (tasks.isEmpty()) {
            System.out.println("[PROJECT HAS NO TASKS]");
            System.out.println();
            return;
        }
        int index = 1;
        for (final Task task : tasks) {
            System.out.println(index++ + " " + task.getName() + ": " + task.getDescription());
            System.out.println("\tBelongs to project: " + task.getProjectId() + " user: " + task.getUserId());
            System.out.println("\tCreated: " + task.getCreationDate() + " Start: " + task.getStartDate() + " End: " + task.getEndDate());
        }
    }

}
