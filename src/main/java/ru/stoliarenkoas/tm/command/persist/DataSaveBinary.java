package ru.stoliarenkoas.tm.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class DataSaveBinary extends AbstractCommand {

    @NotNull
    public static final String NAME = "data-save-bin";
    @NotNull private static final String DESCRIPTION = "saves current data in binary file";

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
        final Collection<Project> projects = getServiceLocator().getProjectService().getAll();
        final Collection<Task> tasks = getServiceLocator().getTaskService().getAll();
        Files.createDirectories(path.getParent());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))){
            outputStream.writeObject(getServiceLocator().getCurrentUser());
            System.out.println("User write: " + getServiceLocator().getCurrentUser());
            outputStream.writeInt(projects.size());
            for (final @NotNull Project project : projects) {
                outputStream.writeObject(project);
            }
            outputStream.writeInt(tasks.size());
            for (final @NotNull Task task : tasks) {
                outputStream.writeObject(task);
            }
        }
        System.out.printf("[BINARY DATA SAVED to %s]%n%n", path.toAbsolutePath());
    }
}
