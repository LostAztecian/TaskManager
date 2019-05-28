package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

import java.io.IOException;

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
    public void run() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) return;
        getServiceLocator().getProjectService().deleteByName(projectName);
        System.out.println("[PROJECT(S) DELETED]");
        System.out.println();
    }

}
