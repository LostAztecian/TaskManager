package ru.stoliarenkoas.tm.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public abstract class Command {

    private final Bootstrap bootstrap;
    private final boolean secured;

    public final void execute() throws IOException {
        if (isPrivate() && bootstrap.getCurrentUser() == null) {
            System.out.println("COMMAND IS NOT AVAILABLE WITHOUT AUTHORIZATION");
            return;
        }
        run();
    }

    protected abstract void run() throws IOException;

    public abstract String getName();
    public abstract String getDescription();
    public final boolean isPrivate() {
        return secured;
    };

    protected Collection<Project> requestProjectsByName() throws IOException {
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) throw new IllegalArgumentException("null request name");
        final Collection<Project> userProjects = getBootstrap().getProjectService().getByIds(getBootstrap().getCurrentUser().getProjectIds());
        final Collection<Project> projectsWithName = userProjects.stream()
                .filter(p -> p.getName().equals(projectName))
                .collect(Collectors.toSet());
        if (projectsWithName.isEmpty()) {
            System.out.println("[NO PROJECTS MATCH GIVEN NAME]");
            System.out.println("[END]");
            System.out.println();
            return null;
        }
        return projectsWithName;
    }

    @Override
    public String toString() {
        return String.format("%s: %s.", getName(), getDescription());
    }

}
