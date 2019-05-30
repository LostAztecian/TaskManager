package tm.client.command.general;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;

public final class HelpCommand extends AbstractCommand {

    @NotNull public static final String NAME = "help";
    @NotNull private static final String DESCRIPTION = "show all commands";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public void run() {
        final String help = getServiceLocator().getServerService().showHelp();
        System.out.println(help);
    }

}
