package tm.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.common.entity.UserDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.service.ServerService;
import tm.server.command.general.AboutCommand;
import tm.server.dto.UserData;
import tm.server.utils.SessionUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@ApplicationScoped
public class ServerServiceImpl implements ServerService {

    @Inject
    private ServiceLocator serviceLocator;

    public ServerServiceImpl() {
    }

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public String showAbout() throws Exception {
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
    public Boolean shutdown(@Nullable final SessionDTO session) throws Exception {
        final UserDTO user = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return false;
        serviceLocator.getEndpoints().forEach(Endpoint::stop);
        System.out.println("[ENDPOINTS STOPPED]");
        serviceLocator.terminate();
        System.out.println("[SERVER TERMINATED]");
        return true; //TODO check if its working after stopping endpoints
    }

    @Override @NotNull
    public String showHelp(@Nullable final SessionDTO session) throws Exception {
        final UserDTO user = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        final boolean loggedIn = user != null;
        final StringBuilder sb = new StringBuilder();
        serviceLocator.getCommands().values()
                .forEach(c -> {if (!c.isPrivate() || loggedIn) sb.append(c).append("\n");});
        return sb.toString();
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws Exception {
        if (comparatorType == null || session == null || !SessionUtil.isValid(session)) return false;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return false;
        session.setSortMethod(comparatorType);
        SessionUtil.sign(session);
        return true;
    }

    @Override @NotNull
    public Boolean dataClearBinary(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveBinary(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        final Collection<ProjectDTO> projects = serviceLocator.getProjectService().getAll(session);
        final Collection<TaskDTO> tasks = serviceLocator.getTaskService().getAll(session);
        Files.createDirectories(path.getParent());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))){
            outputStream.writeObject(currentUser);
            System.out.println("UserDTO write: " + currentUser);
            outputStream.writeInt(projects.size());
            for (@NotNull final ProjectDTO project : projects) {
                outputStream.writeObject(project);
            }
            outputStream.writeInt(tasks.size());
            for (@NotNull final TaskDTO task : tasks) {
                outputStream.writeObject(task);
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadBinary(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/binData/" + currentUser.getName());
        if (Files.notExists(path)) return false;
        Files.createDirectories(path.getParent());
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path, StandardOpenOption.READ))){
            final UserDTO user = (UserDTO)inputStream.readObject();
            System.out.println("UserDTO read: " + user.toString());
            final int numOfProjects = inputStream.readInt();
            for (int i = 0; i < numOfProjects; i++) {
                final ProjectDTO project = (ProjectDTO)inputStream.readObject();
                project.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getProjectService().save(session, project);
                System.out.println("ProjectDTO saved: " + project.toString());
            }
            final int numOfTasks = inputStream.readInt();
            for (int i = 0; i < numOfTasks; i++) {
                final TaskDTO task = (TaskDTO)inputStream.readObject();
                task.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getTaskService().save(session, task);
                System.out.println("TaskDTO saved: " + task.toString());
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + currentUser.getName());
        final UserData userData = new UserData();
        userData.setUser(currentUser);
        userData.setProjects(new ArrayList<>(serviceLocator.getProjectService().getAll(session)));
        userData.setTasks(new ArrayList<>(serviceLocator.getTaskService().getAll(session)));
        Files.createDirectories(path.getParent());

        final Marshaller userMarshaller = JAXBContext.newInstance(UserData.class).createMarshaller();
        userMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        userMarshaller.marshal(userData, path.toFile());
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/xml/" + currentUser.getName());
        if (Files.notExists(path)) return false;
        final Unmarshaller userUnmarshaller = JAXBContext.newInstance(UserData.class).createUnmarshaller();
        final UserData userData = (UserData) userUnmarshaller.unmarshal(path.toFile());
        //clear old data
        serviceLocator.getTaskService().deleteAll(session);
        serviceLocator.getProjectService().deleteAll(session);
        //save new data
        if (userData.getProjects() != null) {
            for (ProjectDTO p : userData.getProjects()) {
                p.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getProjectService().save(session, p);
            }
        }
        if (userData.getTasks() != null) {
            for (TaskDTO t : userData.getTasks()) {
                t.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getTaskService().save(session, t);
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + currentUser.getName());
        final UserData userData = new UserData();
        userData.setUser(currentUser);
        userData.setProjects(new ArrayList<>(serviceLocator.getProjectService().getAll(session)));
        userData.setTasks(new ArrayList<>(serviceLocator.getTaskService().getAll(session)));
        Files.createDirectories(path.getParent());

        final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        properties.put(JAXBContextProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        final JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{UserData.class}, properties);

        final Marshaller userMarshaller = context.createMarshaller();
        userMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        userMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        userMarshaller.setProperty(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        userMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        userMarshaller.marshal(userData, path.toFile());
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/JAXBData/json/" + currentUser.getName());
        if (Files.notExists(path)) return false;

        final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        properties.put(JAXBContextProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        final JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{UserData.class}, properties);

        final Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        unmarshaller.setProperty(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        final StreamSource jsonSource = new StreamSource(path.toFile());
        final UserData userData = unmarshaller.unmarshal(jsonSource, UserData.class).getValue();

        //remove old data
        serviceLocator.getTaskService().deleteAll(session);
        serviceLocator.getProjectService().deleteAll(session);
        //save persisted data
        if (userData.getProjects() != null) {
            for (ProjectDTO p : userData.getProjects()) {
                p.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getProjectService().save(session, p);
            }
        }
        if (userData.getTasks() != null) {
            for (TaskDTO t : userData.getTasks()) {
                t.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getTaskService().save(session, t);
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataClearFasterXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/xml/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/xml/" + currentUser.getName());
        final UserData userData = new UserData();
        userData.setUser(currentUser);
        userData.setProjects(new ArrayList<>(serviceLocator.getProjectService().getAll(session)));
        userData.setTasks(new ArrayList<>(serviceLocator.getTaskService().getAll(session)));
        Files.createDirectories(path.getParent());

        final XmlMapper mapper = new XmlMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), userData);
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/xml/" + currentUser.getName());
        if (Files.notExists(path)) return false;

        final UserData userData = new XmlMapper().readValue(path.toFile(), UserData.class);
        //clear old data
        serviceLocator.getTaskService().deleteAll(session);
        serviceLocator.getProjectService().deleteAll(session);
        //save new data
        if (userData.getProjects() != null) {
            for (ProjectDTO p : userData.getProjects()) {
                p.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getProjectService().save(session, p);
            }
        }
        if (userData.getTasks() != null) {
            for (TaskDTO t : userData.getTasks()) {
                t.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getTaskService().save(session, t);
            }
        }
        return true;
    }

    @Override @NotNull
    public Boolean dataClearFasterJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/json/" + currentUser.getName());
        Files.deleteIfExists(path);
        return true;
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/json/" + currentUser.getName());
        final UserData userData = new UserData();
        userData.setUser(currentUser);
        userData.setProjects(new ArrayList<>(serviceLocator.getProjectService().getAll(session)));
        userData.setTasks(new ArrayList<>(serviceLocator.getTaskService().getAll(session)));
        Files.createDirectories(path.getParent());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), userData);
        return true;
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return false;
        final Path path = Paths.get("TaskManagerSavedData/FasterXml/json/" + currentUser.getName());
        if (Files.notExists(path)) return false;

        final TypeReference<UserData> typeReference = new TypeReference<UserData>(){};
        final UserData userData = new ObjectMapper().readValue(path.toFile(), typeReference);
        //remove old data
        serviceLocator.getTaskService().deleteAll(session);
        serviceLocator.getProjectService().deleteAll(session);
        //save persisted data
        if (userData.getProjects() != null) {
            for (ProjectDTO p : userData.getProjects()) {
                p.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getProjectService().save(session, p);
            }
        }
        if (userData.getTasks() != null) {
            for (TaskDTO t : userData.getTasks()) {
                t.setUserId(currentUser.getId()); //cause users are not persisted
                serviceLocator.getTaskService().save(session, t);
            }
        }
        return true;
    }

}
