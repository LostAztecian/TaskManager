package tm.server.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

public class DataLoadBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-bin";
    @NotNull private static final String DESCRIPTION = "loads saved binary data";

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
        return true;
    }

    @Override
    protected void run() throws Exception {
        final Boolean success = getServiceLocator().getServerService().dataLoadBinary();
        System.out.println(success ? "[BINARY DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
