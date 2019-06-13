package tm.server.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;
import tm.server.command.AbstractCommand;

public class DataClearFasterXmlJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-fe-json";
    @NotNull private static final String DESCRIPTION = "clears any json data saved by fasterxml";

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
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataClearFasterJson(session);
        System.out.println(success ? "[JSON DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
