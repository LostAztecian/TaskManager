package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;

import java.io.IOException;

public class UserLogoutCommand extends Command {

    public static final String NAME = "user-logout";
    private static final String DESCRIPTION = "end user session";

    public UserLogoutCommand(final Bootstrap bootstrap) { super(bootstrap, true); }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() throws IOException {
        getBootstrap().setCurrentUser(null);
        System.out.println("LOGGED OUT");
        System.out.println();
    }

}

