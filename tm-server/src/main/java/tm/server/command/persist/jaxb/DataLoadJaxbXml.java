package tm.server.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.dto.UserData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoadJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "loads saved xml data via JAXB unmarshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataLoadJaxbXml();
        System.out.println(success ? "[XML DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
