package ru.stoliarenkoas.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractCommand implements ru.stoliarenkoas.tm.api.Command {

    @NotNull @Setter
    private ServiceLocator serviceLocator;

    @Override
    public final void execute() throws IOException {
        if (isPrivate() && serviceLocator.getCurrentUser() == null) {
            System.out.println("COMMAND IS NOT AVAILABLE WITHOUT AUTHORIZATION");
            return;
        }
        run();
    }

    protected abstract void run() throws IOException;

    @Override
    public abstract boolean isPrivate();

    @Nullable
    protected Collection<Project> requestProjectsByName() throws IOException {
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) throw new IllegalArgumentException("null request name");
        final Collection<Project> projectsWithName = getServiceLocator()
                .getProjectService()
                .getAllByParentId(getServiceLocator().getCurrentUser().getId())
                .stream()
                .filter(p -> projectName.equals(p.getName()))
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
