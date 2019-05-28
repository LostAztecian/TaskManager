package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.utils.CypherUtil;
import tm.server.utils.InputHelper;
import tm.server.entity.User;

import java.io.IOException;

public class UserChangePasswordCommand extends AbstractCommand {

    @NotNull public static final String NAME = "user-change-password";
    @NotNull private static final String DESCRIPTION = "change password for current user";

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
        final User user = getServiceLocator().getCurrentUser();

        final String pwd = InputHelper.requestLine("ENTER OLD PASSWORD:", false);
        if (pwd == null) return;
        if (!user.getPasswordHash().equals(CypherUtil.getMd5(pwd))) {
            System.out.println("WRONG PASSWORD");
            System.out.println("[END]");
            System.out.println();
        }

        System.out.println("[SET UP NEW PASSWORD]");
        final String newPwd = InputHelper.requestNewPassword();
        if (newPwd == null) return;
        user.setPasswordHash(CypherUtil.getMd5(newPwd));
        getServiceLocator().getUserService().save(user);
        System.out.println("[PASSWORD UPDATED]");
        System.out.println();
    }

}
