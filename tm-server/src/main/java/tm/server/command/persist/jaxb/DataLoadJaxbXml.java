package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

public class DataLoadJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "loads saved xml data via JAXB unmarshaller";

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataLoadJaxbXml(session);
        System.out.println(success ? "[XML DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
