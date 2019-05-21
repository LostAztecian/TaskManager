package ru.stoliarenkoas.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProjectRemoveCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-remove";
    @NotNull private static final String DESCRIPTION = "remove project and all associated tasks";

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
        System.out.println("[PROJECT DELETE]");
        final Collection<Project> projects = InputHelper.requestProjectsByName(getServiceLocator());
        if (projects == null) return;
        final Collection<String> ids = projects.stream().map(Project::getId).collect(Collectors.toSet());
        getServiceLocator().getProjectService().getAllByParentId(getServiceLocator().getCurrentUser().getId()); //method can be invoked only when user != null
        getServiceLocator().getProjectService().deleteByIds(ids);
        System.out.println("[PROJECT(S) DELETED]");
        System.out.println();
    }

}
