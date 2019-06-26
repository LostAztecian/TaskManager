package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.service.TaskService;
import tm.server.entity.Project;
import tm.server.entity.Task;
import tm.server.entity.User;
import tm.server.repository.TaskRepositorySpring;
import tm.server.utils.DatabaseUtil;
import tm.server.utils.SessionUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("spring")
public class TaskServiceSpring implements TaskService {

    @Autowired
    private TaskRepositorySpring repositoryDeltaspike;

    @Autowired
    private ServiceLocator serviceLocator;

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public Collection<TaskDTO> getAll(@Nullable SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        return repositoryDeltaspike.findByProject_User_Id(userId).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        return repositoryDeltaspike.findByProject_User_IdOrderBy(userId, DatabaseUtil.getSortColumn(comparatorType))
                .stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByProject_User_IdAndName(userId, name)
                .stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByProject_User_IdAndNameOrderBy(userId, name, DatabaseUtil.getSortColumn(comparatorType))
                .stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getTasksByProjectId(@Nullable SessionDTO session, @Nullable String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null || userId.isEmpty() || projectId.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByProject_User_IdAndProject_Id(userId, projectId)
                .stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public TaskDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return null;
        return repositoryDeltaspike.findAnyByProject_User_IdAndId(userId, id).toDTO();
    }

    @Override @NotNull
    public Collection<TaskDTO> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || userId.isEmpty() || searchLine.isEmpty()) return Collections.emptyList();
        searchLine = "%" + searchLine + "%";
        return repositoryDeltaspike.search(userId, searchLine).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Boolean save(@Nullable SessionDTO session, @Nullable TaskDTO taskDTO) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || taskDTO == null || userId.isEmpty()) return false;
        if (!userId.equals(taskDTO.getUserId())) return false;
        final User user = new User(serviceLocator.getUserService().get(session, session.getUserId()));
        final Project project = new Project(serviceLocator.getProjectService().get(session, taskDTO.getProjectId()), user);
        repositoryDeltaspike.save(new Task(taskDTO, project));
        return true;
    }

    @Override @NotNull
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return false;
        repositoryDeltaspike.deleteByProject_User_IdAndId(userId, id);
        return true;
    }

    @Override @NotNull
    public Boolean delete(@Nullable SessionDTO session, @Nullable TaskDTO object) throws Exception {
        if (object == null) return false;
        return delete(session, object.getId());
    }

    @Override @NotNull
    public Boolean deleteByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || userId.isEmpty() || ids.isEmpty()) return false;
        for (final String id : ids) {
            repositoryDeltaspike.deleteByProject_User_IdAndId(userId, id);
        }
        return true;
    }

    @Override @NotNull
    public Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return false;
        return repositoryDeltaspike.deleteByProject_User_IdAndName(userId, name) > 0;
    }

    @Override @NotNull
    public Boolean deleteAll(@Nullable SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || userId.isEmpty()) return false;
        return repositoryDeltaspike.deleteByProject_User_Id(userId) > 0;
    }

    @Override @NotNull
    public Collection<String> removeTasksByProjectIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || userId.isEmpty() || ids.isEmpty()) return Collections.emptyList();
        final Set<String> taskIds = new HashSet<>();
        for (final String id : ids) {
            taskIds.addAll(getTasksByProjectId(session, id).stream().map(TaskDTO::getId).collect(Collectors.toList()));
        }
        System.out.println("tasks to delete: " + taskIds);
        deleteByIds(session, taskIds);
        return taskIds;
    }
    
}
