package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.api.repository.PlannedEntityRepository;
import ru.stoliarenkoas.tm.api.repository.TaskRepository;
import ru.stoliarenkoas.tm.api.service.TaskService;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.comparator.ComparatorType;

import java.util.Collection;
import java.util.Collections;

public class TaskServiceImpl extends AbstractService<Task> implements TaskService {

    public TaskServiceImpl(@NotNull final PlannedEntityRepository<Task> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    public void deleteChildrenByParentId(@Nullable final String id) { //no children = no problem

    }

    @Override
    public void deleteChildrenByParentIds(@Nullable final Collection<String> ids) {
        //no children = no problem
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
