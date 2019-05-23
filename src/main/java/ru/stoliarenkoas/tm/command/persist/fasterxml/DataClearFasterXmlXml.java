package ru.stoliarenkoas.tm.command.persist.fasterxml;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearFasterXmlXml extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-fe-xml";
    @NotNull private static final String DESCRIPTION = "clears any xml data saved by fasterxml";

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
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/xml/" + getServiceLocator().getCurrentUser().getName());
        Files.deleteIfExists(path);
        System.out.println("[XML DATA CLEARED]");
        System.out.println();
    }
}
