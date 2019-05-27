package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.api.repository.PlannedEntityRepository;
import ru.stoliarenkoas.tm.api.repository.TaskRepository;
import ru.stoliarenkoas.tm.api.service.TaskService;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskServiceImpl extends AbstractService<Task> implements TaskService {

    public TaskServiceImpl(final @NotNull PlannedEntityRepository<Task> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public void deleteChildrenByParentId(@Nullable String id) { //no children = no problem

    }

    @Override
    public void deleteChildrenByParentIds(@Nullable Collection<String> ids) {
        //no children = no problem
    }

    public @NotNull Collection<Task> getTasksByProjectId(final @Nullable String projectId) {
        final String userId = getCurrentUserId();
        if (userId == null || projectId == null) return Collections.emptySet();
        return ((TaskRepository)repository).findByProjectId(userId, projectId);
    }

    @Override
    public @NotNull Collection<Task> search(@Nullable String searchLine) {
        final String userId = getCurrentUserId();
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        return ((PlannedEntityRepository<Task>)repository).search(userId, searchLine);
    }

    @Override
    public @NotNull Collection<Task> getAllSorted(final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll();
        return ((PlannedEntityRepository<Task>)repository).findAllAndSort(userId, comparatorType);
    }

    @Override
    public @NotNull Collection<Task> getAllByNameSorted(final @Nullable String name, final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(name);
        return ((PlannedEntityRepository<Task>)repository).findByNameAndSort(userId, comparatorType, name);
    }

}
