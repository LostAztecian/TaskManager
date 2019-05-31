package tm.client.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputHelper {

    @NotNull
    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @Nullable
    public static String requestLine(@Nullable final String requestText, boolean allowEmpty) throws IOException {
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
                System.out.println("EMPTY FIELDS ARE NOT ALLOWED");
                System.out.println("type \"END\" TO RETURN");
                System.out.println();
                System.out.println(requestText);
                input = READER.readLine();
            }
        }
        return input;
    }

    @Nullable
    public static Date requestDate(@NotNull final String target) throws IOException {
        final String pattern = "dd.MM.yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        System.out.printf("ENTER %s DATE(%s): %n", target, pattern);
        return formatter.parse(READER.readLine(), new ParsePosition(0));
    }

    @Nullable
    public static String requestNewPassword() throws IOException {
        String userPassword = null, pwdConfirmation;
        while (userPassword == null) {
            userPassword = InputHelper.requestLine("ENTER PASSWORD:", false);
            if (userPassword == null) return null;
            pwdConfirmation = InputHelper.requestLine("CONFIRM PASSWORD:", true);
            if (!userPassword.equals(pwdConfirmation)) {
                System.out.println("PASSWORDS DOESN'T MATCH");
                userPassword = null;
            }
        }
        return userPassword;
    }

}