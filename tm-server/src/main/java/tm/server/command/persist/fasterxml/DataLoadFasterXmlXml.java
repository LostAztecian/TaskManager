package tm.server.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

public class DataLoadFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-fe-xml";
    @NotNull private static final String DESCRIPTION = "loads xml data saved via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataLoadFasterXml(session);
        System.out.println(success ? "[XML DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
