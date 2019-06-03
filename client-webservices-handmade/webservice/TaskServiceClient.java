package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.TaskWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;
import tm.common.entity.Task;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class TaskServiceClient implements TaskWebService {

    private final TaskWebService taskService;

    public TaskServiceClient() {
        TaskWebService taskWebService = null;
        try {
            URL url = new URL("http://localhost:8080/taskService?wsdl");
            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://webservice.server.tm/", "TaskWebServiceBeanService");
            Service service = Service.create(url, qname);
            taskWebService = service.getPort(TaskWebService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        taskService = taskWebService;
    }

    @Override @Nullable
    public Task getNewTask() {
        return taskService.getNewTask();
    }

    @Override @NotNull
    public Collection<Task> getAllTasks(@Nullable final Session session) {
        return taskService.getAllTasks(session);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksSorted(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        return taskService.getAllTasksSorted(session, comparatorType);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByName(@Nullable final Session session, @Nullable final String name) {
        return taskService.getAllTasksByName(session, name);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByNameSorted(@Nullable final Session session, @Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return taskService.getAllTasksByNameSorted(session, name, comparatorType);
    }

    @Override @Nullable
    public Task getTask(@Nullable final Session session, @Nullable final String id) {
        return taskService.getTask(session, id);
    }

    @Override @NotNull
    public Collection<Task> searchTask(@Nullable final Session session, @Nullable final String searchLine) {
        return taskService.searchTask(session, searchLine);
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable final Session session, @Nullable final Task task) {
        return taskService.saveTask(session, task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final Task task) {
        return taskService.deleteTask(session, task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final String id) {
        return taskService.deleteTask(session, id);
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable final Session session, @Nullable final Collection<String> ids) {
        return taskService.deleteTasksByIds(session, ids);
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable final Session session, @Nullable final String name) {
        return taskService.deleteTasksByName(session, name);
    }

    @Override @NotNull
    public Boolean deleteAllTasks(@Nullable final Session session) {
        return taskService.deleteAllTasks(session);
    }

    @Override @NotNull
    public Collection<Task> getTasksByProjectId(@Nullable final Session session, @Nullable final String projectId) {
        return taskService.getTasksByProjectId(session, projectId);
    }
}
