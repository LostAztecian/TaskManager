package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;
import tm.server.command.AbstractCommand;
import tm.server.util.InputHelper;

public class ProjectRemoveCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-remove";
    @NotNull private static final String DESCRIPTION = "remove project and all associated tasks";

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
        System.out.println("[PROJECT DELETE]");
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) return;
        getServiceLocator().getProjectService().deleteByName(session, projectName);
        System.out.println("[PROJECT(S) DELETED]");
        System.out.println();
    }

}
