package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.dto.UserData;

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
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + getServiceLocator().getCurrentUser().getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return;
        }
        final Unmarshaller userUnmarshaller = JAXBContext.newInstance(UserData.class).createUnmarshaller();
        final UserData userData = (UserData) userUnmarshaller.unmarshal(path.toFile());
        //clear old data
        getServiceLocator().getTaskService().deleteAll();
        getServiceLocator().getProjectService().deleteAll();
        getServiceLocator().getUserService().delete(getServiceLocator().getCurrentUser());
        //save new data
        getServiceLocator().getUserService().save(userData.getUser());
        getServiceLocator().setCurrentUser(userData.getUser());
        userData.getProjects().forEach(getServiceLocator().getProjectService()::save);
        userData.getTasks().forEach(getServiceLocator().getTaskService()::save);
        System.out.printf("[XML DATA LOADED from %s VIA JAXB]%n%n", path.toAbsolutePath());
    }
}
