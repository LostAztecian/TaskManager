package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    protected final Repository<T> repository;
    protected final Service<? extends Entity> childService;

    public AbstractService(final Repository<T> repository, final Service<? extends Entity> childService) {
        this.repository = repository;
        this.childService = childService;
    }

    @Override
    public Collection<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Collection<T> getAllByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptySet();
        return repository.findByName(name);
    }

    @Override
    public Collection<T> getAllByParentId(final String id) {
        if (id == null || id.isEmpty()) return Collections.emptySet();
        return repository.findByParentId(id);
    }

    @Override
    public Collection<T> getByIds(final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        final Collection<T> objects = new LinkedHashSet<>();
        ids.forEach(id -> Optional.of(this.get(id)).ifPresent(objects::add));
        return objects;
    }

    @Override
    public T get(final String id) {
        if (id == null || id.isEmpty()) return null;
        return repository.findOne(id);
    }

    @Override
    public void save(final T object) {
        if (object == null || object.getId() == null || object.getId().isEmpty()) return;
        repository.merge(object);
    }

    @Override
    public void delete(final String id) {
        if (id == null || id.isEmpty()) return;
        deleteChildrenByParentId(id);
        repository.remove(id);
    }

    @Override
    public void delete(final T object) {
        if (object == null) return;
        repository.remove(object);
    }

    @Override
    public void deleteChildrenByParentId(final String id) {
        if (childService == null || id == null || id.isEmpty()) return;
        childService.getAll().stream().filter(c -> id.equals(((Entity) c).getParentId())).forEach(c -> childService.delete(((Entity) c).getId()));
    }

    @Override
    public void deleteByName(final String name, boolean allMatches) {
        repository.findByName(name).forEach(o -> this.delete(o.getId()));
    }

    @Override
    public void deleteByIds(final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        ids.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        final Collection<T> objects = getAll();
        objects.forEach(o -> this.delete(o.getId()));
    }

}
