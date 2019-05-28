package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearJaxbJson extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-jaxb-json";
    @NotNull private static final String DESCRIPTION = "clears any saved binary data";

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
    protected void run() throws IOException {
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + getServiceLocator().getCurrentUser().getName());
        Files.deleteIfExists(path);
        System.out.println("[JSON DATA CLEARED]");
        System.out.println();
    }
}
