package tm.server.service.deltaspike;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.annotations.Deltaspike;
import tm.server.api.ServiceLocator;
import tm.server.api.service.TaskService;
import tm.server.repository.deltaspike.TaskRepositoryDeltaspike;
import tm.server.utils.DatabaseUtil;
import tm.server.utils.SessionUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Deltaspike
@Transactional
@ApplicationScoped
public class TaskServiceDeltaspike implements TaskService {

    @Inject
    private TaskRepositoryDeltaspike repositoryDeltaspike;

    @Inject
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
        return repositoryDeltaspike.findByUserId(userId);
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        return repositoryDeltaspike.findByUserIdEqualOrderBy(userId, DatabaseUtil.getSortColumn(comparatorType));
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByUserIdEqualAndNameEqual(userId, name);
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByUserIdEqualAndNameEqualOrderBy(userId, name, DatabaseUtil.getSortColumn(comparatorType));
    }

    @Override @NotNull
    public Collection<TaskDTO> getTasksByProjectId(@Nullable SessionDTO session, @Nullable String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null || userId.isEmpty() || projectId.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByUserIdEqualAndProjectIdEqual(userId, projectId);
    }

    @Override @Nullable
    public TaskDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return null;
        return repositoryDeltaspike.findAnyByUserIdEqualAndIdEqual(userId, id);
    }

    @Override @NotNull
    public Collection<TaskDTO> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || userId.isEmpty() || searchLine.isEmpty()) return Collections.emptyList();
        searchLine = "%" + searchLine + "%";
        return repositoryDeltaspike.search(userId, searchLine);
    }

    @Override @NotNull
    public Boolean save(@Nullable SessionDTO session, @Nullable TaskDTO taskDTO) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || taskDTO == null || userId.isEmpty()) return false;
        if (!userId.equals(taskDTO.getUserId())) return false;
        repositoryDeltaspike.merge(taskDTO);
        return true;
    }

    @Override @NotNull
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return false;
        repositoryDeltaspike.deleteByUserIdEqualAndIdEqual(userId, id);
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
            repositoryDeltaspike.deleteByUserIdEqualAndIdEqual(userId, id);
        }
        return true;
    }

    @Override @NotNull
    public Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return false;
        return repositoryDeltaspike.deleteByUserIdEqualAndNameEqual(userId, name) > 0;
    }

    @Override @NotNull
    public Boolean deleteAll(@Nullable SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || userId.isEmpty()) return false;
        return repositoryDeltaspike.deleteByUserIdEqual(userId) > 0;
    }

    @Override @NotNull
    public Collection<String> removeTasksByProjectIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || userId.isEmpty() || ids.isEmpty()) return Collections.emptyList();
        final Set<String> taskIds = new HashSet<>();
        for (final String id : ids) {
            taskIds.addAll(getTasksByProjectId(session, id).stream().map(TaskDTO::getId).collect(Collectors.toList()));
        }
        deleteByIds(session, taskIds);
        return taskIds;
    }
    
}
