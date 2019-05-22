package ru.stoliarenkoas.tm.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class InputHelper {

    @NotNull
    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @Nullable
    public static String requestLine(final @Nullable String requestText, boolean allowEmpty) throws IOException {
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
    public static Date requestDate(final @NotNull String target) throws IOException {
        final String pattern = "dd.MM.yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        System.out.printf("ENTER %s DATE(%s): %n", target, pattern);
        return formatter.parse(READER.readLine(), new ParsePosition(0));
    }

    @NotNull
    public static String getMd5(final @NotNull String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "corrupted-hash";
        }
    }
    
    @Nullable
    public static String requestNewPassword() throws IOException {
        String userPwd = null, pwdConfirmation;
        while (userPwd == null) {
            userPwd = InputHelper.requestLine("ENTER PASSWORD:", false);
            if (userPwd == null) return null;
            pwdConfirmation = InputHelper.requestLine("CONFIRM PASSWORD:", true);
            if (!userPwd.equals(pwdConfirmation)) {
                System.out.println("PASSWORDS DOESN'T MATCH");
                userPwd = null;
            }
        }
        return userPwd;
    }

    @Nullable
    public static Collection<Project> requestProjectsByName(final @Nullable ServiceLocator serviceLocator) throws IOException {
        final String projectName = InputHelper.requestLine("ENTER PROJECT NAME:", false);
        if (projectName == null) throw new IllegalArgumentException("null request name");
        final Collection<Project> projectsWithName = serviceLocator
                .getProjectService()
                .getAllByParentId(serviceLocator.getCurrentUser().getId())
                .stream()
                .filter(p -> projectName.equals(p.getName()))
                .collect(Collectors.toSet());
        if (projectsWithName.isEmpty()) {
            System.out.println("[NO PROJECTS MATCH GIVEN NAME]");
            System.out.println("[END]");
            System.out.println();
            return null;
        }
        return projectsWithName;
    }
}
