package ru.stoliarenkoas.tm;

import ru.stoliarenkoas.tm.console.ConsoleHelper;
import ru.stoliarenkoas.tm.console.InputHelper;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        final ConsoleHelper consoleHelper = new ConsoleHelper();

        System.out.println("*** WELCOME TO TASK-MANAGER ***");
        try {
            String inputLine;
            MainCycle:
            while ((inputLine = InputHelper.requestLine("enter command", true)) != null) {
                switch (ConsoleCommands.valueOf(inputLine.toUpperCase().replace('-', '_'))) {
                    case EXIT: { break MainCycle; }
                    case HELP: {
                        InputHelper.printHelp();
                        continue;
                    }
                    //region ProjectCommands
                    case PROJECT_CREATE: {
                        consoleHelper.createProject();
                        continue;
                    }
                    case PROJECT_REMOVE: {
                        consoleHelper.removeProject();
                        continue;
                    }
                    case PROJECT_LIST: {
                        consoleHelper.showProjects();
                        continue;
                    }
                    case PROJECT_CLEAR: {
                        consoleHelper.clearProjects();
                        continue;
                    }
                    //endregion
                    //region TaskCommands
                    case TASK_CREATE: {
                        consoleHelper.createTask();
                        continue;
                    }
                    case TASK_REMOVE: {
                        consoleHelper.removeTask();
                        continue;
                    }
                    case TASK_LIST: {
                        consoleHelper.showTasks();
                        continue;
                    }
                    case TASK_CLEAR: {
                        consoleHelper.clearTasks();
                        continue;
                    }
                    //endregion
                    default: {
                        System.out.println("INVALID COMMAND, TYPE EXIT TO END PROGRAM");
                        System.out.println();
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
