package ru.stoliarenkoas.tm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputHelper {

    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static String requestLine(final String requestText, boolean allowEmpty) throws IOException {
        if (requestText == null || requestText.isEmpty()) return null;
        System.out.println(requestText);
        String input = READER.readLine();
        if (!allowEmpty) {
            while (input.isEmpty()) {
                if ("end".equals(input.toLowerCase())) {
                    System.out.println("[CANCELLED]");
                    System.out.println();
                    return null;
                }
                System.out.println("EMPTY NAMES ARE NOT ALLOWED");
                System.out.println("type \"END\" TO RETURN");
                System.out.println();
                System.out.println(requestText);
                input = READER.readLine();
            }
        }
        return input;
    }

    public static Date requestDate() throws IOException {
        final String pattern = "DD.MM.YYYY";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        System.out.println("ENTER DATE("+ pattern + "):");
        return formatter.parse(READER.readLine(), new ParsePosition(0));
    }

    public static void printHelp() {
        System.out.println("exit: End program.");
        System.out.println("help: Show all commands.");
        System.out.println("project-clear: Remove all projects.");
        System.out.println("project-create: Create new project.");
        System.out.println("project-list: Show all projects.");
        System.out.println("project-task-list: Show all tasks for selected projects.");
        System.out.println("project-remove: Remove selected project.");
        System.out.println("task-clear: Remove all tasks from a project.");
        System.out.println("task-create: Create new task in a project.");
        System.out.println("task-list: Show all tasks.");
        System.out.println("task-remove: Remove selected task from a project.");
        System.out.println();
    }

}
