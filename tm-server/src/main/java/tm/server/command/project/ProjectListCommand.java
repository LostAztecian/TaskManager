package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Project;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

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
    public void run() throws Throwable {
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Collection<Project> allProjects = getServiceLocator().getProjectService().getAll(session);
        if (allProjects.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final Project project : allProjects) {
            System.out.println(index++ + " " + project.getName() + ": " + project.getDescription());
            System.out.println("\tBelongs to user: " + project.getUserId());
            System.out.println("\tCreated: " + project.getCreationDate() + " Start: " + project.getStartDate() + " End: " + project.getEndDate());
        }
        System.out.println();
    }

}
