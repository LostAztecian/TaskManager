package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.PlannedEntityRepository;
import tm.server.api.repository.TaskRepository;
import tm.server.api.service.TaskService;
import tm.server.entity.Task;
import tm.server.comparator.ComparatorType;

import java.util.Collection;
import java.util.Collections;

public class TaskServiceImpl extends AbstractService<Task> implements TaskService {

    public TaskServiceImpl(@NotNull final PlannedEntityRepository<Task> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public Boolean deleteChildrenByParentId(@Nullable final String id) { //no children = no problem
        return true;
    }

    @Override
    public Boolean deleteChildrenByParentIds(@Nullable final Collection<String> ids) {
        return true;
    }

   @Override @NotNull
   public Collection<Task> getTasksByProjectId(@Nullable final String projectId) {
        final String userId = getCurrentUserId();
        if (userId == null || projectId == null) return Collections.emptySet();
        return ((TaskRepository)repository).findByProjectId(userId, projectId);
    }

    @Override @NotNull
    public Collection<Task> search(@Nullable final String searchLine) {
        final String userId = getCurrentUserId();
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        return ((PlannedEntityRepository<Task>)repository).search(userId, searchLine);
    }

    @Override @NotNull
    public Collection<Task> getAllSorted(@Nullable final ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll();
        return ((PlannedEntityRepository<Task>)repository).findAllAndSort(userId, comparatorType);
    }

    @Override @NotNull
    public Collection<Task> getAllByNameSorted(@Nullable final String name, @Nullable final ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(name);
        return ((PlannedEntityRepository<Task>)repository).findByNameAndSort(userId, comparatorType, name);
    }

}
