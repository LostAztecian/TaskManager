package tm.client.command.general;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;

import java.io.IOException;

public class AboutCommand extends AbstractCommand {

    @NotNull public static final String NAME = "about";
    @NotNull private static final String DESCRIPTION = "show build information";

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
        return false;
    }

    @Override
    protected void run() throws IOException {
        final String about = getServiceLocator().getServerService().showAbout();
        System.out.println(about);
    }

}
