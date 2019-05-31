package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Project;
import tm.common.entity.Session;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Collection<Project> allProjects = getServiceLocator().getProjectService().getAllProjects(session);
        if (allProjects.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        final Collection<Project> sortedProjects = new TreeSet<>(getServiceLocator().getCurrentSortMethod());
        sortedProjects.addAll(allProjects);
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final Project project : sortedProjects) {
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

}
