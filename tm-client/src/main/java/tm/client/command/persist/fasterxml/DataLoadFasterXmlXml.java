package tm.client.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

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
    protected void run() throws Exception {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataLoadFasterXml(session);
        System.out.println(success ? "[XML DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
