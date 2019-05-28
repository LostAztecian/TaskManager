package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;
import tm.server.entity.User;
import tm.server.service.UserServiceImpl;

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
        final String userLogin = requestNewLogin();
        if (userLogin == null) return;
        final String userPwd = InputHelper.requestNewPassword();
        if (userPwd == null) return;
        ((UserServiceImpl)getServiceLocator().getUserService()).persist(new User(userLogin, userPwd));
    }

    @Nullable
    private String requestNewLogin() throws IOException {
        String userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        if (userLogin == null) return null;
        while (!getServiceLocator().getUserService().getAllByName(userLogin).isEmpty()) {
            System.out.println("USERNAME IS ALREADY TAKEN, PLEASE TRY AGAIN");
            userLogin = InputHelper.requestLine("ENTER LOGIN:", false);
        }
        return userLogin;
    }

}
