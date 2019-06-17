package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

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
    public void run() throws Throwable {
        getServiceLocator().getUserService().logout(getServiceLocator().getCurrentSession());
        getServiceLocator().setCurrentSession(null);
        System.out.println("LOGGED OUT");
    }

}

