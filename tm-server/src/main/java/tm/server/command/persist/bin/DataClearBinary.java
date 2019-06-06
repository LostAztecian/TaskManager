package tm.server.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

import java.io.IOException;

public class DataClearBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-bin";
    @NotNull private static final String DESCRIPTION = "clears any saved binary data";


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
    protected void run() throws Throwable {
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataClearBinary(session);
        System.out.println(success ? "[BINARY DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
