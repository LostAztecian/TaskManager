package tm.server.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

import java.io.IOException;

public class DataClearFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-fe-xml";
    @NotNull private static final String DESCRIPTION = "clears any xml data saved by fasterxml";

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
    protected void run() throws IOException {
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataClearFasterXml(session);
        System.out.println(success ? "[XML DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
