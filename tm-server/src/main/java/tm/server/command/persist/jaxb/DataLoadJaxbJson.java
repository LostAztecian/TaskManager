package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

public class DataLoadJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-jaxb-json";
    @NotNull private static final String DESCRIPTION = "loads saved json data via JAXB marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataLoadJaxbJson();
        System.out.println(success ? "[JSON DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
