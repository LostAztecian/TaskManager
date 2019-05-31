package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

import java.io.IOException;

public class DataClearJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "clears any saved xml data saved by jaxb marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataClearJaxbXml();
        System.out.println(success ? "[XML DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}