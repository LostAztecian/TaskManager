package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
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
    public Collection<Task> getAllTasks(@Nullable final Session session) throws ServerException {
        try {
            return taskService.getAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<Task> getAllTasksSorted(@Nullable final Session session, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return taskService.getAllSorted(session, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByName(@Nullable final Session session, @Nullable final String name) throws ServerException {
        try {
            return taskService.getAllByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<Task> getAllTasksByNameSorted(@Nullable final Session session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return taskService.getAllByNameSorted(session, name, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public Task getTask(@Nullable final Session session, @Nullable final String id) throws ServerException {
        try {
            return taskService.get(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<Task> searchTask(@Nullable final Session session, @Nullable final String searchLine) throws ServerException {
        try {
            return taskService.search(session, searchLine);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable final Session session, @Nullable final Task task) throws ServerException {
        try {
            return taskService.save(session, task);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final Task task) throws ServerException {
        try {
            return taskService.delete(session, task);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final Session session, @Nullable final String id) throws ServerException {
        try {
            return taskService.delete(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable final Session session, @Nullable final Collection<String> ids) throws ServerException {
        try {
            return taskService.deleteByIds(session, ids);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable final Session session, @Nullable final String name) throws ServerException {
        try {
            return taskService.deleteByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteAllTasks(@Nullable final Session session) throws ServerException {
        try {
            return taskService.deleteAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override
    public @NotNull Collection<Task> getTasksByProjectId(@Nullable final Session session, @Nullable final String projectId) throws ServerException {
        try {
            return taskService.getTasksByProjectId(session, projectId);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
}
