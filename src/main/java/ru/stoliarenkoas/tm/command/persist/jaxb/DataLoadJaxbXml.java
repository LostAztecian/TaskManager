package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.command.persist.dto.UserData;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collection;

public class DataLoadJaxbXml extends AbstractCommand {

    @NotNull
    public static final String NAME = "data-load-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "loads saved xml data via JAXB unmarshaller";

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @NotNull
    @Override
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
