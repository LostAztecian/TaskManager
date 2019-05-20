package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;

public class ProjectClearCommand extends AbstractCommand {

    public static final String NAME = "project-clear";
    private static final String DESCRIPTION = "remove all projects";

    public ProjectClearCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() {
        getServiceLocator().getProjectService().deleteByIds(getServiceLocator().getCurrentUser().getProjectIds());
        getServiceLocator().getCurrentUser().getProjectIds().clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
