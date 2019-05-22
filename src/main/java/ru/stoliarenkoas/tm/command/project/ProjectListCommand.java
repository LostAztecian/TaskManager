package ru.stoliarenkoas.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.User;
import ru.stoliarenkoas.tm.entity.comparator.CreationDateComparator;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public class ProjectListCommand extends AbstractCommand {

    @NotNull public static final String NAME = "project-list";
    @NotNull private static final String DESCRIPTION = "show all projects";

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
        final User user = getServiceLocator().getCurrentUser();
        final Collection<Project> allProjects = getServiceLocator().getProjectService()
            .getAllByParentId(user.getId()); //method can be invoked only when user != null
        if (allProjects.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        final Collection<Project> sortedProjects = new TreeSet<>(getServiceLocator().getCurrentSortMethod());
        sortedProjects.addAll(allProjects);
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final Project project : sortedProjects) {
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

}
