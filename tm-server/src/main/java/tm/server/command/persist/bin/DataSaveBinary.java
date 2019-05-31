package tm.server.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.Project;
import tm.common.entity.Task;
import tm.server.command.AbstractCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class DataSaveBinary extends AbstractCommand {

    @NotNull public static final String NAME = "data-save-bin";
    @NotNull private static final String DESCRIPTION = "saves current data in binary file";

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
        final Path path = Paths.get("TaskManagerSavedData/binData/" + getServiceLocator().getCurrentUser().getName());
        final Collection<Project> projects = getServiceLocator().getProjectService().getAll();
        final Collection<Task> tasks = getServiceLocator().getTaskService().getAll();
        Files.createDirectories(path.getParent());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))){
            outputStream.writeObject(getServiceLocator().getCurrentUser());
            System.out.println("User write: " + getServiceLocator().getCurrentUser());
            outputStream.writeInt(projects.size());
            for (@NotNull final Project project : projects) {
                outputStream.writeObject(project);
            }
            outputStream.writeInt(tasks.size());
            for (@NotNull final Task task : tasks) {
                outputStream.writeObject(task);
            }
        }
        System.out.printf("[BINARY DATA SAVED to %s]%n%n", path.toAbsolutePath());
    }
}
