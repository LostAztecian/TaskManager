package ru.stoliarenkoas.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserRegisterCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-register";
    @NotNull private static final String DESCRIPTION = "register a new user";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public void run() throws IOException {
        System.out.println("[REGISTRING NEW USER]");
        final String userLogin = requestNewLogin();
        if (userLogin == null) return;
        final String userPwd = InputHelper.requestNewPassword();
        if (userPwd == null) return;
        getServiceLocator().getUserService().save(new User(userLogin, userPwd));
    }

    @Nullable
    private String requestNewLogin() throws IOException {
        String userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        if (userLogin == null) return null;
        while (!getServiceLocator().getUserService().getAllByName(userLogin).isEmpty()) {
            System.out.println("USERNAME IS ALREADY TAKEN, PLEASE TRY AGAIN");
            userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        }
        return userLogin;
    }

}
