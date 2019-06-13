package tm.client.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

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
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataLoadBinary(session);
        System.out.println(success ? "[BINARY DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
