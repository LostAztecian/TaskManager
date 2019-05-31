package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.repository.PlannedEntityRepository;
import tm.server.api.service.ProjectService;
import tm.server.api.service.Service;
import tm.server.api.ServiceLocator;
import tm.common.entity.Project;
import tm.common.entity.Task;
import tm.common.comparator.ComparatorType;

import java.util.Collection;
import java.util.Collections;

public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {

    public ProjectServiceImpl(@NotNull final PlannedEntityRepository<Project> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public Boolean deleteChildrenByParentId(@Nullable final Session session, @Nullable final String id) {
        final Service<Task> childService = serviceLocator.getTaskService();
        return childService.deleteByIds(session, Collections.singletonList(id));
    }

    @Override
    public Boolean deleteChildrenByParentIds(@Nullable final Session session, @Nullable final Collection<String> ids) {
        final Service<Task> childService = serviceLocator.getTaskService();
        return childService.deleteByIds(session, ids);
    }

    @Override @NotNull
    public Collection<Project> search(@Nullable final Session session, @Nullable final String searchLine) {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        return ((PlannedEntityRepository<Project>)repository).search(userId, searchLine);
    }

    @Override @NotNull
    public Collection<Project> getAllSorted(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll(session);
        return ((PlannedEntityRepository<Project>)repository).findAllAndSort(userId, comparatorType);
    }

    @Override @NotNull
    public Collection<Project> getAllByNameSorted(@Nullable final Session session, @Nullable final String name, @Nullable final ComparatorType comparatorType) {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(session, name);
        return ((PlannedEntityRepository<Project>)repository).findByNameAndSort(userId, comparatorType, name);
    }

    @Override
    public @NotNull Boolean deleteProjectTasks(@Nullable final Session session, @Nullable final String projectId) {
        return deleteChildrenByParentId(session, projectId);
    }
}
