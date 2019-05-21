package ru.stoliarenkoas.tm.console;

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
    public static Date requestDate() throws IOException {
        final String pattern = "DD.MM.YYYY";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        System.out.println("ENTER DATE("+ pattern + "):");
        return formatter.parse(READER.readLine(), new ParsePosition(0));
    }

    @Nullable
    public static String getMd5(final @Nullable String md5) {
        if (md5 == null) return null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
