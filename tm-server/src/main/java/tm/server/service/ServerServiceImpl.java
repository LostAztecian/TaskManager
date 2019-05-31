package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.common.entity.Task;
import tm.common.entity.User;
import tm.server.api.ServiceLocator;
import tm.server.api.service.ServerService;
import tm.server.command.general.AboutCommand;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Properties;

public class ServerServiceImpl implements ServerService {

    private final ServiceLocator serviceLocator;

    public ServerServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override @NotNull
    public String showAbout() {
        String out = "Sorry, unable to find config.properties";
        try (InputStream input = AboutCommand.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                return out;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            out = String.format("AppName: %s, Version: %s, Build#: %s %n%n",
                    prop.getProperty("application.name"),
                    prop.getProperty("application.version"),
                    prop.getProperty("application.build") );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return out;
    }

    @Override @NotNull
    public Boolean shutdown() {
        serviceLocator.getEndpoints().forEach(Endpoint::stop);
        System.out.println("[ENDPOINTS STOPPED]");
        serviceLocator.terminate();
        System.out.println("[SERVER TERMINATED]");
        return true; //TODO check if its working after stopping endpoints
    }

    @Override @NotNull
    public String showHelp() {
        final boolean loggedIn = serviceLocator.getCurrentUser() != null;
        final StringBuilder sb = new StringBuilder();
        serviceLocator.getCommands().values()
                .forEach(c -> {if (!c.isPrivate() || loggedIn) sb.append(c).append("\n");});
        return sb.toString();
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final ComparatorType comparatorType) {
        if (comparatorType == null) return false;
        serviceLocator.setCurrentSortMethod(comparatorType.comparator);
        return true;
    }

    @Override @NotNull
    public Boolean dataClearBinary() throws IOException {
        final User currentUser = serviceLocator.getCurrentUser();
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveBinary() throws IOException {
        final User currentUser = serviceLocator.getCurrentUser();
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        final Collection<Project> projects = serviceLocator.getProjectService().getAll();
        final Collection<Task> tasks = serviceLocator.getTaskService().getAll();
        Files.createDirectories(path.getParent());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))){
            outputStream.writeObject(currentUser);
            System.out.println("User write: " + currentUser);
            outputStream.writeInt(projects.size());
            for (@NotNull final Project project : projects) {
                outputStream.writeObject(project);
            }
            outputStream.writeInt(tasks.size());
            for (@NotNull final Task task : tasks) {
                outputStream.writeObject(task);
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadBinary() throws IOException, ClassNotFoundException {
        final User currentUser = serviceLocator.getCurrentUser();
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        if (Files.notExists(path)) {
            System.out.println("[NO SAVED DATA FOUND]");
            return false;
        }
        Files.createDirectories(path.getParent());
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path, StandardOpenOption.READ))){
            final User user = (User)inputStream.readObject();
            System.out.println("User read: " + user.toString());
            serviceLocator.getUserService().save(user);
            serviceLocator.setCurrentUser(user);
            final int numOfProjects = inputStream.readInt();
            for (int i = 0; i < numOfProjects; i++) {
                final Project project = (Project)inputStream.readObject();
                serviceLocator.getProjectService().save(project);
                System.out.println("Project saved: " + project.toString());
            }
            final int numOfTasks = inputStream.readInt();
            for (int i = 0; i < numOfTasks; i++) {
                final Task task = (Task)inputStream.readObject();
                serviceLocator.getTaskService().save(task);
                System.out.println("Task saved: " + task.toString());
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml() {
        System.out.println("dataClearJaxbXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml() {
        System.out.println("dataSaveJaxbXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml() {
        System.out.println("dataLoadJaxbXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson() {
        System.out.println("dataClearJaxbJson");
        return false;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson() {
        System.out.println("dataSaveJaxbJson");
        return false;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson() {
        System.out.println("dataLoadJaxbJson");
        return false;
    }

    @Override @NotNull
    public Boolean dataClearFasterXml() {
        System.out.println("dataClearFasterXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml() {
        System.out.println("dataSaveFasterXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml() {
        System.out.println("dataLoadFasterXml");
        return false;
    }

    @Override @NotNull
    public Boolean dataClearFasterJson() {
        System.out.println("dataClearFasterJson");
        return false;
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson() {
        System.out.println("dataSaveFasterJson");
        return false;
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson() {
        System.out.println("dataLoadFasterJson");
        return false;
    }

}
