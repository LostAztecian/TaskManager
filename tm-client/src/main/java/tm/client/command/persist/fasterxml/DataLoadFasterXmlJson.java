package tm.client.command.persist.fasterxml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.dto.UserData;

import javax.xml.bind.JAXBContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataLoadFasterXmlJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-fe-json";
    @NotNull private static final String DESCRIPTION = "loads json data saved via fasterxml";

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
        final Boolean success = getServiceLocator().getServerService().dataLoadFasterJson();
        System.out.println(success ? "[JSON DATA LOADED]" : "[DATA LOAD FAILURE]");
    }
}
