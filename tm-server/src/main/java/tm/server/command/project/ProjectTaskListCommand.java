package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.service.TaskService;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

import java.util.Collection;
import java.util.TreeSet;

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
    public void run() throws Throwable {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Collection<ProjectDTO> projects = InputHelper.requestProjectsByName(session, getServiceLocator());
        if (projects == null) return;
//        final Collection<TaskDTO> tasks = new TreeSet<>(getServiceLocator().getCurrentSortMethod());
//        for (final ProjectDTO project : projects) {
//            tasks.addAll(((TaskService)(getServiceLocator().getTaskService()))
//                    .getTasksByProjectId(session, project.getId()));
//        }
//        if (tasks.isEmpty()) {
//            System.out.println("[PROJECT HAS NO TASKS]");
//            System.out.println();
//            return;
//        }
//        int index = 1;
//        for (final TaskDTO task : tasks) {
//            System.out.println(index++ + " " + task.getName() + ": " + task.getDescription());
//            System.out.println("\tBelongs to project: " + task.getProjectId() + " user: " + task.getUserId());
//            System.out.println("\tCreated: " + task.getCreationDate() + " Start: " + task.getStartDate() + " End: " + task.getEndDate());
//        }
    }

}
