package tm.server.command.user;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

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
        final String oldPassword = InputHelper.requestLine("ENTER OLD PASSWORD:", false);
        if (oldPassword == null) return;
        System.out.println("[SET UP NEW PASSWORD]");
        final String newPassword = InputHelper.requestNewPassword();
        if (newPassword == null) return;
        final Boolean success = getServiceLocator().getUserService()
                .changePassword(getServiceLocator().getCurrentSession(), oldPassword, newPassword);
        System.out.println(success ? "[PASSWORD UPDATED]" : "[PASSWORD UPDATE FAILED]");
    }

}
