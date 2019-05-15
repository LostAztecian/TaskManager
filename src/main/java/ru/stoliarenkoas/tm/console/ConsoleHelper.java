package ru.stoliarenkoas.tm.console;

import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleHelper {

    private final Map<String, Project> projectMap = new LinkedHashMap<>();

    //region ProjectMethods
    public void createProject() throws IOException {
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final String putType = projectMap.containsKey(input.toLowerCase()) ? "UPDATED" : "CREATED";
        final Project project = new Project();
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));
        projectMap.put(project.getName().toLowerCase(), project);
        System.out.printf("[PROJECT %s %s] %n%n", project.getName().toUpperCase(), putType);
    }

    public void removeProject() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final Project project = requestProject();
        if (project == null) return;
        projectMap.remove(project.getName());
        System.out.printf("[PROJECT %s DELETED] %n%n", project.getName().toUpperCase());
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
            System.out.printf("%d. %s %n", index++, project);
        }
        System.out.println();
    }

    public void clearProjects() {
        projectMap.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }
    //endregion
    //region TaskMethods
    public void createTask() throws IOException {
        System.out.println("[TASK CREATE]");
        final Project taskProject = requestProject();
        if (taskProject == null) return;

        final String taskName = InputHelper.requestLine("ENTER NAME:", false);
        if (taskName == null) return;

        final String taskDescription = InputHelper.requestLine("ENTER DESCRIPTION:", true);

        Date taskStartDate;
        try {
            taskStartDate = InputHelper.requestDate();
        } catch (IOException e) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskStartDate = new Date();
        }

        final Task task = new Task(taskProject, taskName, taskDescription, taskStartDate);
        taskProject.getTasks().add(task);
        System.out.printf("[TASK %s CREATED] %n%n", task.getName().toUpperCase());
    }

    public void removeTask() throws IOException {
        System.out.println("[TASK DELETE]");
        final Project taskProject = requestProject();
        if (taskProject == null) return;

        final String input = InputHelper.requestLine("ENTER NAME:", true);
        if (input == null || input.isEmpty()
            || taskProject.getTasks().stream().anyMatch(t -> t.getName().equals(input.toLowerCase()))) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }

        taskProject.getTasks().remove(taskProject.getTasks()
                .stream().filter(t -> t.getName().equals(input.toLowerCase())).findAny().get());

        System.out.printf("[TASK %s DELETED] %n%n", input.toUpperCase());
    }

    public void showTasks() throws IOException {
        final Project taskProject = requestProject();
        if (taskProject == null) return;

        if (taskProject.getTasks().isEmpty()) {
            System.out.println("[TASK LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("TASK LIST");
        int index = 1;
        for (final Task task : taskProject.getTasks()) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }

    public void clearTasks() throws IOException {
        final Project taskProject = requestProject();
        if (taskProject == null) return;
        taskProject.getTasks().clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }
    //endregion

    private Project requestProject() throws IOException {
        final String taskProjectName = InputHelper.requestLine("ENTER PROJECT NAME", false);
        if (taskProjectName == null) throw new IllegalArgumentException("null request name");
        if (!projectMap.containsKey(taskProjectName)) {
            System.out.println("[INVALID PROJECT]");
            System.out.println("[END]");
            System.out.println();
            return null;
        }
        return projectMap.get(taskProjectName);
    }

}
