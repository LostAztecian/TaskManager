package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;

public class ProjectCreateCommand extends AbstractCommand {

    public static final String NAME = "project-create";
    private static final String DESCRIPTION = "save new project";

    public ProjectCreateCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final Project project = new Project();
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));
        getServiceLocator().getProjectService().save(project);
        getServiceLocator().getCurrentUser().getProjectIds().add(project.getId());
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName().toUpperCase());
    }

}
