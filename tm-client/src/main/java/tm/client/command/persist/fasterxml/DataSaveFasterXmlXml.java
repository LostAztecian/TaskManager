package tm.client.command.persist.fasterxml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.dto.UserData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaveFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-fe-xml";
    @NotNull private static final String DESCRIPTION = "saves current data in xml file via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveFasterXml();
        System.out.println(success ? "[XML DATA SAVED]" : "[DATA LOAD FAILURE]");
    }
}
