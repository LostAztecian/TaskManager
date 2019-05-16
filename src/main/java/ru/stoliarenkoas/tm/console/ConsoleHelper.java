package ru.stoliarenkoas.tm.console;

import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.repository.ProjectMapRepository;
import ru.stoliarenkoas.tm.repository.TaskMapRepository;
import ru.stoliarenkoas.tm.service.ProjectService;
import ru.stoliarenkoas.tm.service.TaskService;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class ConsoleHelper {

    private TaskService tasks = new TaskService(new TaskMapRepository());
    private ProjectService projects = new ProjectService(new ProjectMapRepository(), tasks);

    //region ProjectMethods
    public void createProject() throws IOException {
        System.out.println("[PROJECT CREATE]");
        final String input = InputHelper.requestLine("ENTER NAME:", false);
        if (input == null) return;
        final Project project = new Project();
        project.setName(input);
        project.setDescription(InputHelper.requestLine("[ENTER DESCRIPTION]", true));
        projects.save(project);
        System.out.printf("[PROJECT %s CREATED] %n%n", project.getName().toUpperCase());
    }

    public void removeProject() throws IOException {
        System.out.println("[PROJECT DELETE]");
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null || projectName.isEmpty()) return;
        projects.deleteByName(projectName, true);
        System.out.printf("[PROJECTS WITH NAME %s DELETED] %n%n", projectName.toUpperCase());
    }

    public void showProjects() {
        Collection<Project> allProjects = projects.getAll();
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

    public void showProjectTasks() throws IOException {
        final Project project = requestProject();
        if (project == null) return;
        final Collection<Task> projectTasks = tasks.getByIds(project.getTaskIds());
        if (projectTasks.isEmpty()) {
            System.out.println("[PROJECT HAS NO TASKS]");
            System.out.println();
            return;
        }
        projectTasks.forEach(System.out::println);
    }

    public void clearProjects() {
        projects.deleteAll();
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
        tasks.save(task);
        taskProject.getTaskIds().add(task.getId());
        System.out.printf("[TASK %s CREATED] %n%n", task.getName().toUpperCase());
    }

    public void removeTask() throws IOException {
        System.out.println("[TASK DELETE]");
        final Project taskProject = requestProject();
        if (taskProject == null) return;

        final String input = InputHelper.requestLine("ENTER NAME:", true);
        if (input == null || input.isEmpty()) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }
        Collection<Task> tasksWithName = tasks.getAllByName(input);
        tasksWithName.retainAll(tasks.getByIds(taskProject.getTaskIds()));
        if (tasksWithName.isEmpty()) {
            System.out.println("[NO SUCH TASK]");
            System.out.println("[END]");
            System.out.println();
            return;
        }

        tasksWithName.forEach(t -> taskProject.getTaskIds().removeIf(id -> id == t.getId()));
        System.out.printf("[TASK %s DELETED] %n%n", input.toUpperCase());
    }

    public void showTasks() throws IOException {
        final Collection<Task> allTasks = tasks.getAll();
        if (allTasks.isEmpty()) {
            System.out.println("[TASK LIST IS EMPTY]");
            System.out.println();
            return;
        }
        System.out.println("TASK LIST");
        int index = 1;
        for (final Task task : allTasks) {
            System.out.printf("%d. %s %n", index++, task);
        }
        System.out.println();
    }

    public void clearTasks() throws IOException {
        final Project taskProject = requestProject();
        if (taskProject == null) return;
        tasks.deleteByIds(taskProject.getTaskIds());
        System.out.printf("[ALL PROJECT %s TASKS REMOVED] %n%n", taskProject.getName());
    }
    //endregion

    private Project requestProject() throws IOException {
        final String taskProjectName = InputHelper.requestLine("ENTER PROJECT NAME", false);
        if (taskProjectName == null) throw new IllegalArgumentException("null request name");
        final Collection<Project> projectsWithGivenName = projects.getAllByName(taskProjectName);
        if (projectsWithGivenName.isEmpty()) {
            System.out.println("[NO SUCH PROJECT]");
            System.out.println("[END]");
            System.out.println();
            return null;
        }
        return projectsWithGivenName.iterator().next();
    }

}
