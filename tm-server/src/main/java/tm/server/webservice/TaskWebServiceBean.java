package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.service.TaskService;
import tm.server.api.webservice.TaskWebService;
import tm.server.comparator.ComparatorType;
import tm.server.entity.Task;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.server.api.webservice.TaskWebService")
public class TaskWebServiceBean implements TaskWebService {

    private final TaskService taskService;

    public TaskWebServiceBean(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public Task getNewTask() {
        final Task task = new Task();
        task.setName("ts001");
        task.setDescription("taskdesc");
        task.setProjectId("prID");
        task.setUserId("uID");

        return task;
    }

    @Override @NotNull
    public Collection<Task> getAllTasks() {
        return taskService.getAll();
    }

    @Override @NotNull
    public Collection<Task> getAllTasksSorted(@Nullable final ComparatorType comparatorType) {
        return taskService.getAllSorted(comparatorType);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByName(@Nullable final String name) {
        return taskService.getAllByName(name);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByNameSorted(@Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return taskService.getAllByNameSorted(name, comparatorType);
    }

    @Override @Nullable
    public Task getTask(@Nullable final String id) {
        return taskService.get(id);
    }

    @Override @NotNull
    public Collection<Task> searchTask(@Nullable final String searchLine) {
        return taskService.search(searchLine);
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable final Task task) {
        return taskService.save(task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Task task) {
        return taskService.delete(task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final String id) {
        return taskService.delete(id);
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable final Collection<String> ids) {
        return taskService.deleteByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable final String name) {
        return taskService.deleteByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllTasks() {
        return taskService.deleteAll();
    }
}
