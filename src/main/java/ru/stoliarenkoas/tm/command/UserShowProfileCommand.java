package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;

public class UserShowProfileCommand extends UserCommand {

    public static final String NAME = "user-show-profile";
    private static final String DESCRIPTION = "display profile of current user";

    public UserShowProfileCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, true);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void run() throws IOException {
        final User user = getServiceLocator().getCurrentUser();
        System.out.println("User: " + user.getLogin());
        System.out.println("User status: " + user.getRole().getDisplayName());
        System.out.println("[TO CHANGE PASSWORD TYPE \'user-change-password\']");
        System.out.println();
    }

}
