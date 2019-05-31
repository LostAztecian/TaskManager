package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.User;
import tm.server.command.AbstractCommand;
import tm.server.service.UserServiceImpl;
import tm.server.utils.InputHelper;

import java.io.IOException;

public class UserRegisterCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-register";
    @NotNull private static final String DESCRIPTION = "register a new user";

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
        System.out.println("[REGISTERING NEW USER]");
        final String login = InputHelper.requestLine("ENTER LOGIN:", false);
        if (login == null) return;
        final String password = InputHelper.requestNewPassword();
        if (password == null) return;
        final Boolean success = getServiceLocator().getUserService().register(login, password);
        System.out.println(success ? "[USER REGISTERED]" : "[USER REGISTER FAILED]");
    }

}
