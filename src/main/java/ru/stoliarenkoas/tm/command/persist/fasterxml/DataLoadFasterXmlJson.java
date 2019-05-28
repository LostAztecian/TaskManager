package ru.stoliarenkoas.tm.command.persist.fasterxml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.dto.UserData;

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
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/json/" + getServiceLocator().getCurrentUser().getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return;
        }

        final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        final JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{UserData.class}, properties);

        final TypeReference<UserData> typeReference = new TypeReference<UserData>(){};
        final UserData userData = new ObjectMapper().readValue(path.toFile(), typeReference);

        //remove old data
        getServiceLocator().getTaskService().deleteAll();
        getServiceLocator().getProjectService().deleteAll();
        getServiceLocator().getUserService().delete(getServiceLocator().getCurrentUser());
        //save persisted data
        getServiceLocator().getUserService().save(userData.getUser());
        getServiceLocator().setCurrentUser(userData.getUser());
        userData.getProjects().forEach(getServiceLocator().getProjectService()::save);
        userData.getTasks().forEach(getServiceLocator().getTaskService()::save);

        System.out.printf("[BINARY DATA LOADED from %s VIA FASTERXML]%n%n", path.toAbsolutePath());
    }
}
