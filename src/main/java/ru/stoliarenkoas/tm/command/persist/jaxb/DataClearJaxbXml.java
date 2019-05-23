package ru.stoliarenkoas.tm.command.persist.jaxb;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearJaxbXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-jaxb-xml";
    @NotNull private static final String DESCRIPTION = "clears any saved xml data saved by jaxb marshaller";

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
    protected void run() throws IOException {
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + getServiceLocator().getCurrentUser().getName());
        Files.deleteIfExists(path);
        System.out.println("[XML DATA CLEARED]");
        System.out.println();
    }
}
