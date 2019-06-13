package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.ProjectDTO;
import tm.common.api.webservice.SessionDTO;

import java.io.IOException;
import java.util.Collection;

public class ProjectSearchCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-search";
    @NotNull private static final String DESCRIPTION = "search projects by name and description";

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
        System.out.println("[PROJECT SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<ProjectDTO> searchResult = getServiceLocator().getProjectService().searchProject(session, searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        System.out.println("PROJECTS MATCHING CRITERIA:");
        int index = 1;
        for (final ProjectDTO project : searchResult) {
            System.out.println(index++ + " " + project.getName() + ": " + project.getDescription());
            System.out.println("\tBelongs to user: " + project.getUserId());
            System.out.println("\tCreated: " + project.getCreationDate() + " Start: " + project.getStartDate() + " End: " + project.getEndDate());
        }
        System.out.println();
    }

}
