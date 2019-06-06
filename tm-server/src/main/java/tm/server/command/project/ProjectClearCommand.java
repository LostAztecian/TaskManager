package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

public class ProjectClearCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-clear";
    @NotNull private static final String DESCRIPTION = "remove all projects";

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
        getServiceLocator().getProjectService().deleteAll(session);
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
