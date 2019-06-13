package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.ProjectDTO;
import tm.common.api.webservice.SessionDTO;

import java.io.IOException;
import java.util.Collection;

public class ProjectListCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-list";
    @NotNull private static final String DESCRIPTION = "show all projects";

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
        final Collection<ProjectDTO> allProjects = getServiceLocator().getProjectService().getAllProjects(session);
        if (allProjects.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final ProjectDTO project : allProjects) {
            System.out.println(index++ + " " + project.getName() + ": " + project.getDescription());
            System.out.println("\tBelongs to user: " + project.getUserId());
            System.out.println("\tCreated: " + project.getCreationDate() + " Start: " + project.getStartDate() + " End: " + project.getEndDate());
        }
        System.out.println();
    }

}
