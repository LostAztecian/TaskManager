package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.dto.UserData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataLoadJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-jaxb-json";
    @NotNull private static final String DESCRIPTION = "loads saved json data via JAXB marshaller";

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
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + getServiceLocator().getCurrentUser().getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return;
        }

        final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        final JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{UserData.class}, properties);

        final Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        final StreamSource jsonSource = new StreamSource(path.toFile());
        final UserData userData = unmarshaller.unmarshal(jsonSource, UserData.class).getValue();

        //remove old data
        getServiceLocator().getTaskService().deleteAll();
        getServiceLocator().getProjectService().deleteAll();
        getServiceLocator().getUserService().delete(getServiceLocator().getCurrentUser());
        //save persisted data
        getServiceLocator().getUserService().save(userData.getUser());
        getServiceLocator().setCurrentUser(userData.getUser());
        userData.getProjects().forEach(getServiceLocator().getProjectService()::save);
        userData.getTasks().forEach(getServiceLocator().getTaskService()::save);

        System.out.printf("[BINARY DATA LOADED from %s VIA JAXB]%n%n", path.toAbsolutePath());
    }
}
