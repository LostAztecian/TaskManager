package tm.client.command.project;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

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
    public void run() {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        getServiceLocator().getProjectService().deleteAllProjects(session);
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
