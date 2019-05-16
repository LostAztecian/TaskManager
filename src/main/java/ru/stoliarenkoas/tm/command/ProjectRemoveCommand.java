package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;

import java.io.IOException;

public class ProjectRemoveCommand extends Command {

    public static final String NAME = "project-remove";
    private static final String DESCRIPTION = "remove project and all associated tasks";

    public ProjectRemoveCommand(final Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void execute() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null || projectName.isEmpty()) return;
        getBootstrap().getProjectService().deleteByName(projectName, true);
        System.out.printf("[PROJECTS WITH NAME \'%s\' DELETED] %n%n", projectName.toUpperCase());
    }

}
