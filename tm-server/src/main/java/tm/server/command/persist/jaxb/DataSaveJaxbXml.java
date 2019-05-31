package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.dto.UserData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaveJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "saves current data in xml file via JAXB marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveJaxbXml();
        System.out.println(success ? "[XML DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
