package tm.client.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

public class DataSaveFasterXmlJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-fe-json";
    @NotNull private static final String DESCRIPTION = "saves current data json file via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveFasterJson(session);
        System.out.println(success ? "[JSON DATA SAVED]" : "[DATA LOAD FAILURE]");
    }
}
