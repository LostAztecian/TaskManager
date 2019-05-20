package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;

import java.util.Collection;

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
        getServiceLocator().getProjectService().getAllByParentId(getServiceLocator().getCurrentUser().getId())
                .forEach(p -> getServiceLocator().getTaskService().deleteChildrenByParentId(p.getId()));
        getServiceLocator().getProjectService().deleteChildrenByParentId(getServiceLocator().getCurrentUser().getId());
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
