package tm.client.command.user;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.utils.InputHelper;

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
        System.out.println("[REGISTRING NEW USER]");
        final String userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        if (userLogin == null) return;
        final String userPassword = InputHelper.requestNewPassword();
        if (userPassword == null) return;
        final Boolean success = getServiceLocator().getUserService().register(userLogin, userPassword);
        System.out.println(success ? "[USER CREATED]" : "[FAILED TO CREATE NEW USER]");
        System.out.println();
    }

}
