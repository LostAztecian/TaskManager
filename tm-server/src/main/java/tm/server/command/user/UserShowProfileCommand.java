package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

public class UserShowProfileCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-show-profile";
    @NotNull private static final String DESCRIPTION = "display profile of current user";

    @Override @NotNull
    public String getName() {
        return NAME;
    }

    @Override @NotNull
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public void run() throws Throwable {
        final String userProfile = getServiceLocator().getUserService()
                .showUserProfile(getServiceLocator().getCurrentSession());
        System.out.println(userProfile);
    }

}
