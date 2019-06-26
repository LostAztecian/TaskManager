package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.server.command.AbstractCommand;
import tm.server.util.InputHelper;

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
    public void run() throws Throwable {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        System.out.println("[PROJECT SEARCH]");
        final String searchRequest = InputHelper.requestLine("ENTER TEXT TO SEARCH:", true);
        final Collection<ProjectDTO> searchResult = getServiceLocator().getProjectService()
                .search(session, searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        final Collection<ProjectDTO> sortedProjects = new TreeSet<>(session.getSortMethod().comparator);
        sortedProjects.addAll(searchResult);
        System.out.println("PROJECTS MATCHING CRITERIA:");
        int index = 1;
        for (final ProjectDTO project : sortedProjects) {
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

}
