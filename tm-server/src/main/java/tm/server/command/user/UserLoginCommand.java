package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.server.api.service.UserService;
import tm.server.command.AbstractCommand;
import tm.server.utils.CypherUtil;
import tm.server.utils.InputHelper;
import tm.server.entity.User;

import java.io.IOException;
import java.util.Optional;

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
        final String pwdHash = CypherUtil.getMd5(userPassword);
        final Optional<User> user = ((UserService)getServiceLocator().getUserService()).validate(userLogin, pwdHash);
        getServiceLocator().setCurrentUser(user.get());
        System.out.printf("[LOGGED IN AS %s] %n%n", user.get().getLogin());
    }

    private void printAuthFailed() {
        System.out.println("[INVALID COMBINATION OF USER AND PASSWORD]");
        System.out.println("[END]");
        System.out.println();
    }

}
