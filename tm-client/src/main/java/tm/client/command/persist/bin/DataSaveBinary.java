package tm.client.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.common.entity.Session;

import java.io.IOException;

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
        final Session session = getServiceLocator().getCurrentSession();
        if (session == null) return;
        final Boolean success = getServiceLocator().getServerService().dataSaveBinary(session);
        System.out.println(success ? "[BINARY DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
