package tm.client.command.user;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.User;
import tm.client.utils.InputHelper;

import java.io.IOException;

public class UserLoginCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-login";
    @NotNull private static final String DESCRIPTION = "authorize user for further work";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public void run() throws IOException {
        System.out.println("[AUTHORIZATION]");
        final String userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        if (userLogin == null) return;
        final String userPassword = InputHelper.requestLine("ENTER PASSWORD:", true);
        if (userPassword == null || userPassword.isEmpty()) {
            printAuthFailed();
            return;
        }
        final User user = getServiceLocator().getUserService().login(userLogin, userPassword);
        if (user == null) {
            printAuthFailed();
            return;
        }
        getServiceLocator().setCurrentUser(user);
        System.out.printf("[LOGGED IN AS %s] %n%n", user.getLogin());
    }

    private void printAuthFailed() {
        System.out.println("[INVALID COMBINATION OF USER AND PASSWORD]");
        System.out.println("[END]");
        System.out.println();
    }

}