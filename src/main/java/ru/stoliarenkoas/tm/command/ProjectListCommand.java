package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;
import java.util.Collection;

public class ProjectListCommand extends Command {

    public static final String NAME = "project-list";
    private static final String DESCRIPTION = "show all projects";

    public ProjectListCommand(final Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        final User user = getBootstrap().getCurrentUser();
        final Collection<Project> allProjects = getBootstrap().getProjectService().getByIds(user.getProjectIds());
        if (allProjects.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final Project project : allProjects) {
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

}