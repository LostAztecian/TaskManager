package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    @NotNull
    protected final Repository<T> repository;
    @Nullable
    protected final Service<? extends Entity> childService;

    public AbstractService(final @NotNull Repository<T> repository,
                           final @Nullable Service<? extends Entity> childService) {
        this.repository = repository;
        this.childService = childService;
    }

    @NotNull
    @Override
    public Collection<T> getAll() {
        return repository.findAll();
    }

    @NotNull
    @Override
    public Collection<T> getAllByName(final @Nullable String name) {
        if (name == null || name.isEmpty()) return Collections.emptySet();
        return repository.findByName(name);
    }

    @NotNull
    @Override
    public Collection<T> getAllByParentId(final @Nullable String id) {
        if (id == null || id.isEmpty()) return Collections.emptySet();
        return repository.findByParentId(id);
    }

    @NotNull
    @Override
    public Collection<T> getByIds(final @Nullable Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        final Collection<T> objects = new LinkedHashSet<>();
        ids.forEach(id -> Optional.of(this.get(id)).ifPresent(objects::add));
        return objects;
    }

    @Override
    public T get(final @Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        return repository.findOne(id);
    }

    @Override
    public void save(final @Nullable T object) {
        if (object == null || object.getId().isEmpty()) return;
        repository.merge(object);
    }

    @Override
    public void delete(final @Nullable String id) {
        if (id == null || id.isEmpty()) return;
        deleteChildrenByParentId(id);
        repository.remove(id);
    }

    @Override
    public void delete(final @Nullable T object) {
        if (object == null) return;
        repository.remove(object);
    }

    @Override
    public void deleteChildrenByParentId(final @Nullable String id) {
        if (childService == null || id == null || id.isEmpty()) return;
        childService.getAll().stream().filter(c -> id.equals(((Entity) c).getParentId())).forEach(c -> childService.delete(((Entity) c).getId()));
    }

    @Override
    public void deleteByName(final @Nullable String name, boolean allMatches) {
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
