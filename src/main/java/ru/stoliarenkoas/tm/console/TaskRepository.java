package ru.stoliarenkoas.tm.console;

import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TaskRepository {
    
    private final Map<String, Task> taskMap = new LinkedHashMap<>();

    public  void createTask() throws IOException {
        System.out.println("[TASK CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) {
            System.out.println("[CANCELLED]");
            System.out.println();
            return;
        }
        final String putType = taskMap.containsKey(input.toLowerCase()) ? "UPDATED" : "CREATED";
        final Task task = new Task();
        task.setName(input);
        taskMap.put(task.getName().toLowerCase(), task);
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
            System.out.printf("%d. %s %n", index++, task.getName());
        }
        System.out.println();
    }

    public void clearTasks() {
        taskMap.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
        System.out.println();
    }
    
}
