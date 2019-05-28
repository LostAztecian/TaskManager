package ru.stoliarenkoas.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;

public class UserLogoutCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-logout";
    @NotNull private static final String DESCRIPTION = "end user session";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
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

