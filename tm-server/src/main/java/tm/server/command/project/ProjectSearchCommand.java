package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Project;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

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
        final Collection<Project> searchResult = getServiceLocator().getProjectService()
                .search(session, searchRequest);
        if (searchRequest == null || searchRequest.isEmpty()) {
            System.out.println("[FOUND NOTHING]");
            System.out.println();
            return;
        }
        final Collection<Project> sortedProjects = new TreeSet<>(getServiceLocator().getCurrentSortMethod());
        sortedProjects.addAll(searchResult);
        System.out.println("PROJECTS MATCHING CRITERIA:");
        int index = 1;
        for (final Project project : sortedProjects) {
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

}
