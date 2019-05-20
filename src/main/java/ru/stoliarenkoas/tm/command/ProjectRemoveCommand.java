package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProjectRemoveCommand extends AbstractCommand {

    public static final String NAME = "project-remove";
    private static final String DESCRIPTION = "remove project and all associated tasks";

    public ProjectRemoveCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final Collection<Project> projects = requestProjectsByName();
        if (projects == null) return;
        final Collection<String> ids = projects.stream().map(Project::getId).collect(Collectors.toSet());
        getServiceLocator().getCurrentUser().getProjectIds().removeAll(ids);
        getServiceLocator().getProjectService().deleteByIds(ids);
        System.out.println("[PROJECT(S) DELETED");
        System.out.println();
    }

}
