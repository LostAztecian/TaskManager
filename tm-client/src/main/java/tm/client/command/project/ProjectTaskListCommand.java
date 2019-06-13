package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.ProjectDTO;
import tm.common.api.webservice.SessionDTO;
import tm.common.api.webservice.TaskDTO;

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
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) return;
        final Collection<ProjectDTO> projects = getServiceLocator().getProjectService().getProjectsByName(session, projectName);
        final Collection<TaskDTO> tasks = new ArrayList<>();
        for (final ProjectDTO project : projects) {
            tasks.addAll(getServiceLocator().getTaskService().getTasksByProjectId(session, project.getId()));
        }
        if (tasks.isEmpty()) {
            System.out.println("[PROJECT HAS NO TASKS]");
            System.out.println();
            return;
        }
        int index = 1;
        for (final TaskDTO task : tasks) {
            System.out.println(index++ + " " + task.getName() + ": " + task.getDescription());
            System.out.println("\tBelongs to project: " + task.getProjectId() + " user: " + task.getUserId());
            System.out.println("\tCreated: " + task.getCreationDate() + " Start: " + task.getStartDate() + " End: " + task.getEndDate());
        }
    }

}
