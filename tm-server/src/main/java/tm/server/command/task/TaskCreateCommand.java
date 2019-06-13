package tm.server.command.task;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class TaskCreateCommand extends AbstractCommand {

    @NotNull public static final String NAME = "task-create";
    @NotNull private static final String DESCRIPTION = "save task for a selected project";

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
        System.out.println("[TASK CREATE]");
        final Collection<ProjectDTO> projects = InputHelper.requestProjectsByName(session, getServiceLocator());
        if (projects == null || projects.isEmpty()) return;
        final Optional<ProjectDTO> project = projects.stream().findFirst();
        final String taskName = InputHelper.requestLine("ENTER NAME:", false);
        if (taskName == null) return;

        final String taskDescription = InputHelper.requestLine("ENTER DESCRIPTION:", true);

        Date taskStartDate = InputHelper.requestDate("START");
        if (taskStartDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskStartDate = new Date();
        }

        Date taskEndDate = InputHelper.requestDate("END");
        if (taskEndDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskEndDate = new Date();
        }

        final TaskDTO task = new TaskDTO(session.getUserId(), taskName);
        task.setProjectId(project.get().getId());
        task.setDescription(taskDescription);
        task.setStartDate(taskStartDate);
        task.setEndDate(taskEndDate);
        getServiceLocator().getTaskService().save(session, task);
        System.out.printf("[TASK \'%s\' CREATED] %n%n", task.getName());
    }

}
