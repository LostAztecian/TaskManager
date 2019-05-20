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

    public AbstractService(final Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Collection<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Collection<T> getAllByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.EMPTY_SET;
        return repository.findByName(name);
    }

    @Override
    public Collection<T> getByIds(final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.EMPTY_SET;
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
    public abstract void delete(final String id);

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
