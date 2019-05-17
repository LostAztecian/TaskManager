package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserLoginCommand extends UserCommand {

    public static final String NAME = "user-login";
    private static final String DESCRIPTION = "authorize user for further work";

    public UserLoginCommand(final Bootstrap bootstrap) {
        super(bootstrap, false);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        System.out.println("[AUTHORIZATION]");
        final String userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        if (userLogin == null) return;
        final String userPassword = InputHelper.requestLine("ENTER PASSWORD:", true);
        if (userPassword == null || userPassword.isEmpty()) {
            printAuthFailed();
            return;
        }
        final User user = getBootstrap().getUserService().getByLogin(userLogin);
        final String pwdHash = InputHelper.getMd5(userPassword);
        if (user == null || pwdHash == null || !pwdHash.equals(user.getPwdHash())) {
            printAuthFailed();
            return;
        }
        getBootstrap().setCurrentUser(user);
        System.out.printf("[LOGGED IN AS %s] %n%n", user.getLogin());
    }

    private void printAuthFailed() {
        System.out.println("[INVALID COMBINATION OF USER AND PASSWORD]");
        System.out.println("[END]");
        System.out.println();
    }

}
