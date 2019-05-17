package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;

public class ProjectCreateCommand extends Command {

    public static final String NAME = "project-create";
    private static final String DESCRIPTION = "create new project";

    public ProjectCreateCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
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
        getBootstrap().getProjectService().save(project);
        getBootstrap().getCurrentUser().getProjectIds().add(project.getId());
        System.out.printf("[PROJECT \'%s\' CREATED] %n%n", project.getName().toUpperCase());
    }

}
