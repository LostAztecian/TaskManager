package tm.client.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.dto.UserData;

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
//        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + getServiceLocator().getCurrentUser().getName());
//        final UserData userData = new UserData();
//        userData.setUser(getServiceLocator().getCurrentUser());
//        userData.setProjects(new ArrayList<>(getServiceLocator().getProjectService().getAll()));
//        userData.setTasks(new ArrayList<>(getServiceLocator().getTaskService().getAll()));
//        Files.createDirectories(path.getParent());
//
//        final Marshaller userMarshaller = JAXBContext.newInstance(UserData.class).createMarshaller();
//        userMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        userMarshaller.marshal(userData, path.toFile());
//        System.out.printf("[XML DATA SAVED to %s VIA JAXB]%n%n", path.toAbsolutePath());
    }
}
