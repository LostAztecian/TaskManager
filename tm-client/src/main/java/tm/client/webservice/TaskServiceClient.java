package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.TaskWebService;
import tm.common.comparator.ComparatorType;
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
    public Collection<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override @NotNull
    public Collection<Task> getAllTasksSorted(@Nullable ComparatorType comparatorType) {
        return taskService.getAllTasksSorted(comparatorType);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByName(@Nullable String name) {
        return taskService.getAllTasksByName(name);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType) {
        return taskService.getAllTasksByNameSorted(name, comparatorType);
    }

    @Override @Nullable
    public Task getTask(@Nullable String id) {
        return taskService.getTask(id);
    }

    @Override @NotNull
    public Collection<Task> searchTask(@Nullable String searchLine) {
        return taskService.searchTask(searchLine);
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable Task task) {
        return taskService.saveTask(task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable Task task) {
        return taskService.deleteTask(task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable String id) {
        return taskService.deleteTask(id);
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable Collection<String> ids) {
        return taskService.deleteTasksByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable String name) {
        return taskService.deleteTasksByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllTasks() {
        return taskService.deleteAllTasks();
    }

    @Override @NotNull
    public Collection<Task> getTasksByProjectId(@Nullable String projectId) {
        return taskService.getTasksByProjectId(projectId);
    }
}
