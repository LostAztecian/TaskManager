package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.dto.UserData;

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
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + getServiceLocator().getCurrentUser().getName());
        final UserData userData = new UserData();
        userData.setUser(getServiceLocator().getCurrentUser());
        userData.setProjects(new ArrayList<>(getServiceLocator().getProjectService().getAll()));
        userData.setTasks(new ArrayList<>(getServiceLocator().getTaskService().getAll()));
        Files.createDirectories(path.getParent());

        final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        final JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{UserData.class}, properties);

        final Marshaller userMarshaller = context.createMarshaller();
        userMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        userMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        userMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        userMarshaller.marshal(userData, path.toFile());
        System.out.printf("[XML DATA SAVED to %s VIA JAXB]%n%n", path.toAbsolutePath());
    }
}
