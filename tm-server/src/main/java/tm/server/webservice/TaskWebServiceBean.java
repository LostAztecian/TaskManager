package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.service.TaskService;
import tm.common.api.webservice.TaskWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Task;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.TaskWebService")
public class TaskWebServiceBean implements TaskWebService {

    private final TaskService taskService;

    public TaskWebServiceBean(@NotNull final TaskService taskService) {
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
    public Collection<Task> getAllTasks(@Nullable final Session session) {
        return taskService.getAll(session);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksSorted(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        return taskService.getAllSorted(session, comparatorType);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByName(@Nullable final Session session, @Nullable final String name) {
        return taskService.getAllByName(session, name);
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByNameSorted(@Nullable final Session session, @Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return taskService.getAllByNameSorted(session, name, comparatorType);
    }

    @Override @Nullable
    public Task getTask(@Nullable final Session session, @Nullable final String id) {
        return taskService.get(session, id);
    }

    @Override @NotNull
    public Collection<Task> searchTask(@Nullable final Session session, @Nullable final String searchLine) {
        return taskService.search(session, searchLine);
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable final Session session, @Nullable final Task task) {
        return taskService.save(session, task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final Task task) {
        return taskService.delete(session, task);
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final String id) {
        return taskService.delete(session, id);
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable final Session session, @Nullable final Collection<String> ids) {
        return taskService.deleteByIds(session, ids);
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable final Session session, @Nullable final String name) {
        return taskService.deleteByName(session, name);
    }

    @Override @NotNull
    public Boolean deleteAllTasks(@Nullable final Session session) {
        return taskService.deleteAll(session);
    }

    @Override
    public @NotNull Collection<Task> getTasksByProjectId(@Nullable final Session session, @Nullable final String projectId) {
        return taskService.getTasksByProjectId(session, projectId);
    }
}
