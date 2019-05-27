package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.api.repository.PlannedEntityRepository;
import ru.stoliarenkoas.tm.api.service.ProjectService;
import ru.stoliarenkoas.tm.api.service.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {

    public ProjectServiceImpl(final @NotNull PlannedEntityRepository<Project> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public void deleteChildrenByParentId(final @Nullable String id) {
        final Service<Task> childService = serviceLocator.getTaskService();
        childService.deleteByIds(Collections.singletonList(id));
    }

    @Override
    public void deleteChildrenByParentIds(final @Nullable Collection<String> ids) {
        final Service<Task> childService = serviceLocator.getTaskService();
        childService.deleteByIds(ids);
    }

    @Override
    public @NotNull Collection<Project> search(final @Nullable String searchLine) {
        final String userId = getCurrentUserId();
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        return ((PlannedEntityRepository<Project>)repository).search(userId, searchLine);
    }

    @Override
    public @NotNull Collection<Project> getAllSorted(final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll();
        return ((PlannedEntityRepository<Project>)repository).findAllAndSort(userId, comparatorType);
    }

    @Override
    public @NotNull Collection<Project> getAllByNameSorted(final @Nullable String name, final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(name);
        return ((PlannedEntityRepository<Project>)repository).findByNameAndSort(userId, comparatorType, name);
    }

}
