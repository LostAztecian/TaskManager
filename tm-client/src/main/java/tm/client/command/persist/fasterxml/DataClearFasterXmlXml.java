package tm.client.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-fe-xml";
    @NotNull private static final String DESCRIPTION = "clears any xml data saved by fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataClearFasterXml();
        System.out.println(success ? "[XML DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
