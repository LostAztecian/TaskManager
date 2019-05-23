package ru.stoliarenkoas.tm.command.persist.bin;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class DataLoadBinary extends AbstractCommand {

    @NotNull
    public static final String NAME = "data-load-bin";
    @NotNull private static final String DESCRIPTION = "loads saved binary data";

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
        final Path path = Paths.get("TaskManagerSavedData/binData/" + getServiceLocator().getCurrentUser().getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return;
        }
        Files.createDirectories(path.getParent());
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path, StandardOpenOption.READ))){
            final User user = (User)inputStream.readObject();
            System.out.println("User read: " + user.toString());
            getServiceLocator().getUserService().save(user);
            getServiceLocator().setCurrentUser(user);
            final int numOfProjects = inputStream.readInt();
            for (int i = 0; i < numOfProjects; i++) {
                final Project project = (Project)inputStream.readObject();
                getServiceLocator().getProjectService().save(project);
                System.out.println("Project saved: " + project.toString());
            }
            final int numOfTasks = inputStream.readInt();
            for (int i = 0; i < numOfTasks; i++) {
                final Task task = (Task)inputStream.readObject();
                getServiceLocator().getTaskService().save(task);
                System.out.println("Task saved: " + task.toString());
            }
        }
        System.out.printf("[BINARY DATA LOADED from %s]%n%n", path.toAbsolutePath());
    }
}
