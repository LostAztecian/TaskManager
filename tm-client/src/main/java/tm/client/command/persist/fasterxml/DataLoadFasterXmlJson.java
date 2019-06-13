package tm.client.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

public class DataLoadFasterXmlJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-fe-json";
    @NotNull private static final String DESCRIPTION = "loads json data saved via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataLoadFasterJson(session);
        System.out.println(success ? "[JSON DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
