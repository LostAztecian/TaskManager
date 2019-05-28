package ru.stoliarenkoas.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserShowProfileCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-show-profile";
    @NotNull private static final String DESCRIPTION = "display profile of current user";

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws IOException {
        final User user = getServiceLocator().getCurrentUser();
        System.out.println("User: " + user.getLogin()); //method can not be invoked when user == null
        System.out.println("User status: " + user.getRole().getDisplayName());
        System.out.println("[TO CHANGE PASSWORD TYPE \'user-change-password\']");
        System.out.println();
    }

}
