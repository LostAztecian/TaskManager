package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.service.TaskService;
import tm.common.api.webservice.TaskWebService;
import tm.common.comparator.ComparatorType;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.TaskWebService")
public class TaskWebServiceBean implements TaskWebService {

    private final TaskService taskService;

    public TaskWebServiceBean(@NotNull final TaskService taskService) {
        this.taskService = taskService;
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllTasks(@Nullable final SessionDTO session) throws ServerException {
        try {
            return taskService.getAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllTasksSorted(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return taskService.getAllSorted(session, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllTasksByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return taskService.getAllByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllTasksByNameSorted(@Nullable final SessionDTO session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return taskService.getAllByNameSorted(session, name, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public TaskDTO getTask(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            return taskService.get(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<TaskDTO> searchTask(@Nullable final SessionDTO session, @Nullable final String searchLine) throws ServerException {
        try {
            return taskService.search(session, searchLine);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean saveTask(@Nullable final SessionDTO session, @Nullable final TaskDTO task) throws ServerException {
        try {
            return taskService.save(session, task);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final SessionDTO session, @Nullable final TaskDTO task) throws ServerException {
        try {
            return taskService.delete(session, task);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTask(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            return taskService.delete(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTasksByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws ServerException {
        try {
            return taskService.deleteByIds(session, ids);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteTasksByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return taskService.deleteByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteAllTasks(@Nullable final SessionDTO session) throws ServerException {
        try {
            return taskService.deleteAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override
    public @NotNull Collection<TaskDTO> getTasksByProjectId(@Nullable final SessionDTO session, @Nullable final String projectId) throws ServerException {
        try {
            return taskService.getTasksByProjectId(session, projectId);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
}
