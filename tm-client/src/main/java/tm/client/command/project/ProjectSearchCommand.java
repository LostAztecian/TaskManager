package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.Project;
import tm.common.api.webservice.Session;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[PROJECT SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<Project> searchResult = getServiceLocator().getProjectService().searchProject(session, searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        System.out.println("PROJECTS MATCHING CRITERIA:");
        int index = 1;
        for (final Project project : searchResult) {
            System.out.println(index++ + " " + project.getName() + ": " + project.getDescription());
            System.out.println("\tBelongs to user: " + project.getUserId());
            System.out.println("\tCreated: " + project.getCreationDate() + " Start: " + project.getStartDate() + " End: " + project.getEndDate());
        }
        System.out.println();
    }

}
