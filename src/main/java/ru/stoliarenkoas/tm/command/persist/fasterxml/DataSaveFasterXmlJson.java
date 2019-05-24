package ru.stoliarenkoas.tm.command.persist.fasterxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.command.persist.dto.UserData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaveFasterXmlJson extends AbstractCommand {

    @NotNull
    public static final String NAME = "data-save-fe-json";
    @NotNull private static final String DESCRIPTION = "saves current data json file via fasterxml";

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
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/json/" + getServiceLocator().getCurrentUser().getName());
        final UserData userData = new UserData();
        userData.setUser(getServiceLocator().getCurrentUser());
        userData.setProjects(new ArrayList<>(getServiceLocator().getProjectService().getAll()));
        userData.setTasks(new ArrayList<>(getServiceLocator().getTaskService().getAll()));
        Files.createDirectories(path.getParent());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), userData);

        System.out.printf("[XML DATA SAVED to %s VIA FASTERXML]%n%n", path.toAbsolutePath());
    }
}
