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

}
