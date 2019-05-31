package tm.client.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Session;

public class DataSaveFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-fe-xml";
    @NotNull private static final String DESCRIPTION = "saves current data in xml file via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveFasterXml(session);
        System.out.println(success ? "[XML DATA SAVED]" : "[DATA LOAD FAILURE]");
    }
}
