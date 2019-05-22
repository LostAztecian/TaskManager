package ru.stoliarenkoas.tm.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataClearBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-clear-bin";
    @NotNull private static final String DESCRIPTION = "clears any saved binary data";

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
        final Path path = Paths.get("/binData/" + getServiceLocator().getCurrentUser().getName());
        Files.deleteIfExists(path);
        System.out.println("[BINARY DATA CLEARED]");
        System.out.println();
    }
}
