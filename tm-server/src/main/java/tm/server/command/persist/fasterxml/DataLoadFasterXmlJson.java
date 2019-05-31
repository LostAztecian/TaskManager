package tm.server.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Session;
import tm.server.command.AbstractCommand;

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataLoadFasterJson(session);
        System.out.println(success ? "[JSON DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
