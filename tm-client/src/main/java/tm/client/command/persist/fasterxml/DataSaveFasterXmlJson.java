package tm.client.command.persist.fasterxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.dto.UserData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaveFasterXmlJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-fe-json";
    @NotNull private static final String DESCRIPTION = "saves current data json file via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveFasterJson();
        System.out.println(success ? "[JSON DATA SAVED]%n%n" : "[DATA LOAD FAILURE]");
    }
}
