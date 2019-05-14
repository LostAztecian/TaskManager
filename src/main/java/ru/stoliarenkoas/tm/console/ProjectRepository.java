package ru.stoliarenkoas.tm.console;

import ru.stoliarenkoas.tm.entity.Project;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository {

    private final Map<String, Project> projectMap = new LinkedHashMap<>();

    public void createProject() throws IOException {
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) {
            System.out.println("[CANCELLED]");
            System.out.println();
            return;
        }
        final String putType = projectMap.containsKey(input.toLowerCase()) ? "UPDATED" : "CREATED";
        final Project project = new Project();
        project.setName(input);
        projectMap.put(project.getName().toLowerCase(), project);
        System.out.printf("[PROJECT %s %s] %n%n", project.getName().toUpperCase(), putType);
    }

    public void removeProject() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final String input = InputHelper.requestLine("ENTER NAME:", true);
        if (input == null || input.isEmpty() || !projectMap.containsKey(input.toLowerCase())) {
            System.out.println("[NO SUCH PROJECT]");
            System.out.println("[END]");
            System.out.println();
            return;
        }
        projectMap.remove(input.toLowerCase());
        System.out.printf("[PROJECT %s DELETED] %n%n", input.toUpperCase());
    }

    public void showProjects() {
        if (projectMap.isEmpty()) {
            System.out.println("[PROJECT LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("PROJECT LIST");
        int index = 1;
        for (final Project project : projectMap.values()) {
            System.out.printf("%d. %s %n", index++, project.getName());
        }
        System.out.println();
    }

    public void clearProjects() {
        projectMap.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }
    
}
