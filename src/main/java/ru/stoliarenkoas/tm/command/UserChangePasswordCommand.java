package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;
import ru.stoliarenkoas.tm.console.InputHelper;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserChangePasswordCommand extends UserCommand {

    public static final String NAME = "user-change-password";
    private static final String DESCRIPTION = "change password for current user";

    public UserChangePasswordCommand(Bootstrap bootstrap) { super(bootstrap, true); }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        final User user = getBootstrap().getCurrentUser();

        final String pwd = InputHelper.requestLine("ENTER OLD PASSWORD:", false);
        if (pwd == null) return;
        if (!user.getPwdHash().equals(InputHelper.getMd5(pwd))) {
            System.out.println("WRONG PASSWORD");
            System.out.println("[END]");
            System.out.println();
        }

        System.out.println("[SET UP NEW PASSWORD]");
        final String newPwd = requestNewPassword();
        if (newPwd == null) return;
        user.setPwdHash(InputHelper.getMd5(newPwd));
        getBootstrap().getUserService().update(user);
        System.out.println("[PASSWORD UPDATED]");
        System.out.println();
    }

}
