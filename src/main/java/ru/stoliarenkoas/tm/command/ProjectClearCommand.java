package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;

public class ProjectClearCommand extends Command {

    public static final String NAME = "project-clear";
    private static final String DESCRIPTION = "remove all projects";

    public ProjectClearCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() {
        getBootstrap().getProjectService().deleteByIds(getBootstrap().getCurrentUser().getProjectIds());
        getBootstrap().getCurrentUser().getProjectIds().clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
