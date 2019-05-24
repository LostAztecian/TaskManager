package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.*;
import ru.stoliarenkoas.tm.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.User;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    @NotNull
    protected final Repository<T> repository;
    @NotNull
    protected final ServiceLocator serviceLocator;

    AbstractService(final @NotNull Repository<T> repository,
                           final @NotNull ServiceLocator serviceLocator) {
        this.repository = repository;
        this.serviceLocator = serviceLocator;
    }

    protected abstract Service<? extends Entity> getChildService();

    @Nullable
    private String getCurrentUserId() {
        if (serviceLocator.getCurrentUser() == null) return null;
        return serviceLocator.getCurrentUser().getId();
    }

    @NotNull
    @Override
    public Collection<T> getAll() {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        return repository.findAll(userId);
    }

    @Override
    public @NotNull Collection<T> getAllSorted(final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        if (comparatorType == null) return getAll();
        return repository.findAllAndSort(userId, comparatorType);
    }

    @NotNull
    @Override
    public Collection<T> getAllByName(final @Nullable String name) {
        final String userId = getCurrentUserId();
        if (name == null || name.isEmpty() || userId == null) return Collections.emptySet();
        return repository.findByName(userId, name);
    }

    @Override
    public @NotNull Collection<T> getAllByNameSorted(final @Nullable String name, final @Nullable ComparatorType comparatorType) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null) return Collections.emptySet();
        if (comparatorType == null) return getAllByName(name);
        return repository.findByNameAndSort(userId, comparatorType, name);
    }

    @Override
    public @NotNull Collection<T> search(@Nullable String searchLine) {
        final String userId = getCurrentUserId();
        if (searchLine == null || searchLine.isEmpty() || userId == null) return Collections.emptySet();
        if (repository instanceof UserRepository) return Collections.emptySet();
        return repository.search(userId, searchLine);
    }

    @Override @Nullable
    public T get(final @Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        final String userId = getCurrentUserId();
        if (userId == null) return null;
        return repository.findOne(userId, id);
    }

    @Override
    public void save(final @Nullable T object) {
        final String userId = getCurrentUserId();
        if (userId == null) return;
        if (object == null || object.getId().isEmpty()) return;
        repository.merge(userId, object);
    }

    @Override
    public void delete(final @Nullable String id) {
        if (id == null || id.isEmpty() || get(id) == null) return;
        final String userId = getCurrentUserId();
        if (userId == null) return;
        final String deletedId = repository.remove(userId, id);
        deleteChildrenByParentId(deletedId);
    }

    @Override
    public void delete(final @Nullable T object) {
        final String userId = getCurrentUserId();
        if (userId == null || object == null) return;
        final String deletedId = repository.remove(userId, object);
        deleteChildrenByParentId(deletedId);
    }

    @Override
    public void deleteChildrenByParentId(final @Nullable String id) {
        if (id == null || id.isEmpty() || get(id) == null) return;
        final Service<? extends Entity> childService = getChildService();
        if (childService == null) return;
        childService.deleteByIds(Arrays.asList(id));
    }

    public void deleteChildrenByParentIds(final @Nullable Collection<String> ids) {
        final Service<? extends Entity> childService = getChildService();
        if (childService == null) return;
        childService.deleteByIds(ids);
    }

    @Override
    public void deleteByName(final @Nullable String name) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null || name.isEmpty()) return;
        final Collection<String> deletedIds = repository.removeByName(userId, name);
        deleteChildrenByParentIds(deletedIds);
    }

    @Override
    public void deleteByIds(final @Nullable Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        ids.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        final String userId = getCurrentUserId();
        if (userId == null) return;
        final Collection<String> deletedIds = repository.removeAll(userId);
        deleteChildrenByParentIds(deletedIds);
    }

}
