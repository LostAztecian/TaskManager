package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.BindHelper;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.ProjectDTO;
import tm.common.api.webservice.SessionDTO;

import java.io.IOException;
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
    public void run() throws IOException {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final ProjectDTO project = new ProjectDTO();
        project.setUserId(session.getUserId());
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));

        Date projectStartDate = InputHelper.requestDate("START");
        if (projectStartDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectStartDate = new Date();
        }
        project.setStartDate(BindHelper.toXMLGregorianCalendar(projectStartDate));

        Date projectEndDate = InputHelper.requestDate("END");
        if (projectEndDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectEndDate = new Date();
        }
        project.setEndDate(BindHelper.toXMLGregorianCalendar(projectEndDate));

        getServiceLocator().getProjectService().saveProject(session, project);
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName());
    }

}
