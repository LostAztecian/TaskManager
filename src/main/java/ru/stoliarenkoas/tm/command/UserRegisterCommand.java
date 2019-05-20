package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserRegisterCommand extends UserCommand {

    public static final String NAME = "user-register";
    private static final String DESCRIPTION = "register a new user";

    public UserRegisterCommand(final ServiceLocator serviceLocator) { super(serviceLocator, false); }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[REGISTRING NEW USER]");
        final String userLogin = requestNewLogin();
        if (userLogin == null) return;
        final String userPwd = requestNewPassword();
        if (userPwd == null) return;
        getServiceLocator().getUserService().save(new User(userLogin, userPwd));
    }

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
