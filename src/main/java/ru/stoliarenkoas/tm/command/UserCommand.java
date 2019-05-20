package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.console.InputHelper;

import java.io.IOException;

abstract class UserCommand extends AbstractCommand {

    UserCommand(final ServiceLocator serviceLocator, boolean secured) {
        super(serviceLocator, secured);
    }

    String requestNewPassword() throws IOException {
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

}
