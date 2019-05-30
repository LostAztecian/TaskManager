package tm.client.command.persist.jaxb;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.dto.UserData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSaveJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-jaxb-json";
    @NotNull private static final String DESCRIPTION = "saves current data in json file via JAXB Marshaller";

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
        final Boolean success = getServiceLocator().getServerService().dataSaveJaxbJson();
        System.out.println(success ? "[JSON DATA SAVED]" : "[DATA SAVE FAILURE]");
    }
}
