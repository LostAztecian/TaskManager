package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.PlannedEntityRepository;
import tm.server.api.repository.TaskRepository;
import tm.server.api.service.TaskService;

import java.util.Collection;
import java.util.Collections;

public class TaskServiceImpl extends AbstractService<TaskDTO> implements TaskService {

    public TaskServiceImpl(@NotNull final PlannedEntityRepository<TaskDTO> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public Boolean deleteChildrenByParentId(@Nullable final SessionDTO session, @Nullable final String id) throws Exception { //no children = no problem
        return true;
    }

    @Override
    public Boolean deleteChildrenByParentIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        return true;
    }

    @Override @NotNull
    public Collection<TaskDTO> getTasksByProjectId(@Nullable final SessionDTO session, @Nullable final String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null) return Collections.emptySet();
        return ((TaskRepository)repository).findByProjectId(userId, projectId);
    }

    @Override
    public @NotNull Collection<String> removeTasksByProjectIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null || ids == null || ids.isEmpty() || session == null) return Collections.emptySet();
            final Collection<String> result = ((TaskRepository)repository).removeByProjectIds(session.getUserId(), ids);
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Collection<TaskDTO> search(@Nullable final SessionDTO session, @Nullable final String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        return ((PlannedEntityRepository<TaskDTO>)repository).search(userId, searchLine);
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllSorted(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll(session);
        return ((PlannedEntityRepository<TaskDTO>)repository).findAllAndSort(userId, comparatorType);
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByNameSorted(@Nullable final SessionDTO session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(session, name);
        return ((PlannedEntityRepository<TaskDTO>)repository).findByNameAndSort(userId, comparatorType, name);
    }

}
