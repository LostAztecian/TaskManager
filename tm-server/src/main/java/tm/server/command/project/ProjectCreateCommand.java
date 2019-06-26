package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.server.command.AbstractCommand;
import tm.server.util.InputHelper;

import java.util.Date;

public class ProjectCreateCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-create";
    @NotNull private static final String DESCRIPTION = "save new project";

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
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final ProjectDTO project = new ProjectDTO(session.getUserId());
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));

        Date projectStartDate = InputHelper.requestDate("START");
        if (projectStartDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectStartDate = new Date();
        }
        project.setStartDate(projectStartDate);

        Date projectEndDate = InputHelper.requestDate("END");
        if (projectEndDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectEndDate = new Date();
        }
        project.setEndDate(projectEndDate);

        getServiceLocator().getProjectService().save(session, project);
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName());
    }

}
