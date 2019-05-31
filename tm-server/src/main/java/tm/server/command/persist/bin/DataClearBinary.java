package tm.server.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-bin";
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
        final Boolean success = getServiceLocator().getServerService().dataClearBinary();
        System.out.println(success ? "[BINARY DATA CLEARED]" : "[DATA CLEAR FAILURE]");
    }
}
