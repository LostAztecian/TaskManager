package ru.stoliarenkoas.tm.console;

import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TaskRepository {
    
    private final Map<String, Task> taskMap = new LinkedHashMap<>();
    private final ProjectRepository projectRepository;

    public TaskRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createTask() throws IOException {
        System.out.println("[TASK CREATE]");
        final String taskName = InputHelper.requestLine("ENTER NAME:", false);
        if (taskName == null) return;
        final String putType = taskMap.containsKey(taskName.toLowerCase()) ? "UPDATED" : "CREATED";

        final String taskDescription = InputHelper.requestLine("ENTER DESCRIPTION:", true);

        final String taskProjectName = InputHelper.requestLine("ENTER PROJECT NAME", false);
        if (taskProjectName == null) return;
        if (!projectRepository.containsProject(taskProjectName)) {
            System.out.println("[INVALID PROJECT]");
            System.out.println("[END]");
            System.out.println();
            return;
        }
        final Project taskProject = projectRepository.getProjectByName(taskProjectName);

        Date taskStartDate;
        try {
            taskStartDate = InputHelper.requestDate();
        } catch (IOException e) {
            System.out.println("[DATE INPUT ERROR, DATE SET TO CURRENT]");
            taskStartDate = new Date();
        }

        final Task task = new Task(taskProject, taskName, taskDescription, taskStartDate);
        taskMap.put(taskName.toLowerCase(), task);
        System.out.printf("[TASK %s %s] %n%n", task.getName().toUpperCase(), putType);
    }

    public void removeTask() throws IOException {
        System.out.println("[TASK DELETE]");
        final String input = InputHelper.requestLine("ENTER NAME:", true);
        if (input == null || input.isEmpty() || !taskMap.containsKey(input.toLowerCase())) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }
        taskMap.remove(input.toLowerCase());
        System.out.printf("[TASK %s DELETED] %n%n", input.toUpperCase());
    }

    public void showTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("[TASK LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("TASK LIST");
        int index = 1;
        for (final Task task : taskMap.values()) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }

    public void clearTasks() {
        taskMap.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }
    
}
