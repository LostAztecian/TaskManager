package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

public class DataSaveJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-jaxb-json";
    @NotNull private static final String DESCRIPTION = "saves current data in json file via JAXB Marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveJaxbJson(session);
        System.out.println(success ? "[JSON DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
