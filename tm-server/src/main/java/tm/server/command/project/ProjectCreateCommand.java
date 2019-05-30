package tm.server.command.project;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;
import tm.common.entity.Project;

import java.io.IOException;
import java.util.Date;

public class ProjectCreateCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-create";
    @NotNull private static final String DESCRIPTION = "save new project";

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
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final Project project = new Project(getServiceLocator().getCurrentUser().getId()); //method can be invoked only when user != null
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));

        Date projectStartDate = InputHelper.requestDate("START");
        if (projectStartDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectStartDate = new Date();
        }
        project.setStartDate(projectStartDate);

        Date projectEndDate = InputHelper.requestDate("END");
        if (projectEndDate == null) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            projectEndDate = new Date();
        }
        project.setEndDate(projectEndDate);

        getServiceLocator().getProjectService().save(project);
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName());
    }

}
