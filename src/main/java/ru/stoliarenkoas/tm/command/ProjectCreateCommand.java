package ru.stoliarenkoas.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;

public class ProjectCreateCommand extends AbstractCommand {

    public static final String NAME = "project-create";
    private static final String DESCRIPTION = "save new project";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
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
        final Project project = new Project(getServiceLocator().getCurrentUser().getId());
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));
        getServiceLocator().getProjectService().save(project);
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName().toUpperCase());
    }

}
