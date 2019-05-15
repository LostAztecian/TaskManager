package ru.stoliarenkoas.tm;

import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.console.ProjectRepository;
import ru.stoliarenkoas.tm.console.TaskRepository;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        final ProjectRepository projectRepository = new ProjectRepository();
        final TaskRepository taskRepository = new TaskRepository(projectRepository);

        System.out.println("*** WELCOME TO TASK-MANAGER ***");
        try {
            String inputLine;
            while ((inputLine = InputHelper.requestLine("enter command", true)) != null) {

                if ("exit".equals(inputLine.toLowerCase())) break;
                if ("help".equals(inputLine.toLowerCase())) {
                    InputHelper.printHelp();
                    continue;
                }

                //region Project-commands
                if ("project-create".equals(inputLine.toLowerCase())) {
                    projectRepository.createProject();
                    continue;
                }
                if ("project-remove".equals(inputLine.toLowerCase())) {
                    projectRepository.removeProject();
                    continue;
                }
                if ("project-list".equals(inputLine.toLowerCase())) {
                    projectRepository.showProjects();
                    continue;
                }
                if ("project-clear".equals(inputLine.toLowerCase())) {
                    projectRepository.clearProjects();
                    continue;
                }
                //endregion

                //region Task-commands
                if ("task-create".equals(inputLine.toLowerCase())) {
                    taskRepository.createTask();
                    continue;
                }
                if ("task-remove".equals(inputLine.toLowerCase())) {
                    taskRepository.removeTask();
                    continue;
                }
                if ("task-list".equals(inputLine.toLowerCase())) {
                    taskRepository.showTasks();
                    continue;
                }
                if ("task-clear".equals(inputLine.toLowerCase())) {
                    taskRepository.clearTasks();
                    continue;
                }
                //endregion
                
                System.out.println("INVALID COMMAND, TYPE EXIT TO END PROGRAM");
                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
