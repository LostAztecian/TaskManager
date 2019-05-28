package tm.server.command.persist.fasterxml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;
import tm.server.dto.UserData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoadFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-load-fe-xml";
    @NotNull private static final String DESCRIPTION = "loads xml data saved via fasterxml";

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
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/xml/" + getServiceLocator().getCurrentUser().getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return;
        }
        final UserData userData = new XmlMapper().readValue(path.toFile(), UserData.class);
        //clear old data
        getServiceLocator().getTaskService().deleteAll();
        getServiceLocator().getProjectService().deleteAll();
        getServiceLocator().getUserService().delete(getServiceLocator().getCurrentUser());
        //save new data
        getServiceLocator().getUserService().save(userData.getUser());
        getServiceLocator().setCurrentUser(userData.getUser());
        userData.getProjects().forEach(getServiceLocator().getProjectService()::save);
        userData.getTasks().forEach(getServiceLocator().getTaskService()::save);

        System.out.printf("[XML DATA LOADED from %s VIA FASTERXML]%n%n", path.toAbsolutePath());
    }
}
