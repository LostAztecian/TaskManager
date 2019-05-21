package ru.stoliarenkoas.tm.command;

import org.jetbrains.annotations.NotNull;

public class ProjectClearCommand extends AbstractCommand {

    public static final String NAME = "project-clear";
    private static final String DESCRIPTION = "remove all projects";

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
    public void run() {
        getServiceLocator().getProjectService().getAllByParentId(getServiceLocator().getCurrentUser().getId())
                .forEach(p -> getServiceLocator().getTaskService().deleteChildrenByParentId(p.getId()));
        getServiceLocator().getProjectService().deleteChildrenByParentId(getServiceLocator().getCurrentUser().getId());
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }

}
