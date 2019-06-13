package tm.client.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.api.webservice.SessionDTO;

import java.io.IOException;

public class DataClearJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-jaxb-json";
    @NotNull private static final String DESCRIPTION = "clears any saved binary data";

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
    protected void run() throws IOException {
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataClearJaxbJson(session);
        System.out.println(success ? "[JSON DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
