package tm.client.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Session;

public class DataSaveJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "saves current data in xml file via JAXB marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveJaxbXml(session);
        System.out.println(success ? "[XML DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
