package ru.stoliarenkoas.tm.command;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UserLogoutCommand extends AbstractCommand {

    public static final String NAME = "user-logout";
    private static final String DESCRIPTION = "end user session";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws IOException {
        getServiceLocator().setCurrentUser(null);
        System.out.println("LOGGED OUT");
        System.out.println();
    }

}

