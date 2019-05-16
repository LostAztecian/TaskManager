package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.Collection;

public class ProjectListCommand extends Command {

    public static final String NAME = "project-list";
    private static final String DESCRIPTION = "show all projects";

    public ProjectListCommand(final Bootstrap bootstrap) {
        super(bootstrap, NAME, DESCRIPTION);
    }

    @Override
    public void execute() throws IOException {
        Collection<Project> allProjects = getBootstrap().getProjectService().getAll();
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
