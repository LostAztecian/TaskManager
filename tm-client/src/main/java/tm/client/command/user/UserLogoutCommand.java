package tm.client.command.user;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;

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
        final Boolean success = getServiceLocator().getUserService().logout();
        if (success) {
            getServiceLocator().setCurrentUser(null);
        }
        System.out.println(success ? "[LOGGED OUT]" : "[FAILED TO LOG OUT]");
        System.out.println();
    }

}

