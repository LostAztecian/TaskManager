package tm.client.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Project;
import tm.common.entity.Task;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class DataSaveBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-bin";
    @NotNull private static final String DESCRIPTION = "saves current data in binary file";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveBinary();
        System.out.println(success ? "[BINARY DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
