package ru.stoliarenkoas.tm.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public abstract class Command {

    private final Bootstrap bootstrap;
    private final String name;
    private final String description;

    public abstract void execute() throws IOException;

    protected Project requestProject() throws IOException {
        final String taskProjectName = InputHelper.requestLine("ENTER PROJECT NAME", false);
        if (taskProjectName == null) throw new IllegalArgumentException("null request name");
        final Collection<Project> projectsWithGivenName = bootstrap.getProjectService().getAllByName(taskProjectName);
        if (projectsWithGivenName.isEmpty()) {
            System.out.println("[NO SUCH PROJECT]");
            System.out.println("[END]");
            System.out.println();
            return null;
        }
        return projectsWithGivenName.iterator().next();
    }

    @Override
    public String toString() {
        return String.format("%s: %s.", name, description);
    }

}
