package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.PlannedEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
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

    @Nullable
    protected abstract String getUserId(final @NotNull T object);

    @Nullable
    protected abstract Service<? extends Entity> getChildService();

    @Nullable
    private String getCurrentUserId() {
        if (serviceLocator.getCurrentUser() == null) return null;
        return serviceLocator.getCurrentUser().getId();
    }

    @NotNull
    private Collection<T> filterByCurrentUser(final @NotNull Collection<T> collection) {
        if (this instanceof UserServiceImpl) return collection;
        final String currentUserId = getCurrentUserId();
        if (currentUserId == null) return Collections.emptySet();
        return collection.stream()
                .filter(p -> currentUserId.equals(getUserId(p)))
                .collect(Collectors.toSet());
    }

    @Nullable
    private T filterByCurrentUser(final @Nullable T object) {
        if (object == null) return null;
        final String currentUserId = getCurrentUserId();
        if (currentUserId == null) return null;
        if (currentUserId.equals(getUserId(object))) return object;
        return null;
    }

    @NotNull
    @Override
    public Collection<T> getAll() {
        return filterByCurrentUser(repository.findAll());
    }

    @NotNull
    @Override
    public Collection<T> getAllByName(final @Nullable String name) {
        if (name == null || name.isEmpty()) return Collections.emptySet();
        return filterByCurrentUser(repository.findByName(name));
    }

    @NotNull
    @Override
    public Collection<T> getAllByParentId(final @Nullable String id) {
        if (id == null || id.isEmpty()) return Collections.emptySet();
        return filterByCurrentUser(repository.findByParentId(id));
    }

    @NotNull
    @Override
    public Collection<T> getByIds(final @Nullable Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        final Collection<T> objects = new LinkedHashSet<>();
        ids.forEach(id -> {if (id != null) Optional.ofNullable(this.get(id)).ifPresent(objects::add);});
        return filterByCurrentUser(objects);
    }

    @Override
    public @NotNull Collection<T> search(@Nullable String searchLine) {
        if (searchLine == null || searchLine.isEmpty()) return Collections.emptySet();
        final Collection<T> objects = repository.findAll();
        if (objects.isEmpty() || !PlannedEntity.class.isAssignableFrom(objects.stream().findAny().get().getClass())) return Collections.emptySet();
        final Collection<T> searchResult = repository.findAll().stream().map(t -> ((PlannedEntity)t))
                .filter(t -> Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                        Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .map(t -> ((T)t)) //cast back to source
                .collect(Collectors.toSet());
        return filterByCurrentUser(searchResult);
    }

    @Override @Nullable
    public T get(final @Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        return filterByCurrentUser(repository.findOne(id));
    }

    @Override
    public void save(final @Nullable T object) {
        if (object == null || object.getId().isEmpty() || filterByCurrentUser(object) == null) return;
        repository.merge(object);
    }

    @Override
    public void delete(final @Nullable String id) {
        if (id == null || id.isEmpty() || get(id) == null) return;
        deleteChildrenByParentId(id);
        repository.remove(id);
    }

    @Override
    public void delete(final @Nullable T object) {
        if (object == null || filterByCurrentUser(object) == null) return;
        repository.remove(object);
    }

    @Override
    public void deleteChildrenByParentId(final @Nullable String id) {
        if (id == null || id.isEmpty() || get(id) == null) return;
        final Service<? extends Entity> childService = getChildService();
        if (childService == null) return;
        childService.getAll().stream().filter(c -> id.equals(((Entity) c).getParentId())).forEach(c -> childService.delete(((Entity) c).getId()));
    }

    @Override
    public void deleteByName(final @Nullable String name, boolean allMatches) {
        if (name == null || name.isEmpty()) return;
        repository.findByName(name).forEach(o -> this.delete(o.getId()));
    }

    @Override
    public void deleteByIds(final @Nullable Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        ids.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        final Collection<T> objects = getAll();
        objects.forEach(o -> this.delete(o.getId()));
    }

}
