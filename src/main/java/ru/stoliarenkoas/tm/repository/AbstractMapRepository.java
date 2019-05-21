package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMapRepository<T extends Entity> implements Repository {

    @NotNull
    protected final Map<String, T> map = new LinkedHashMap<>();

    @NotNull
    @Override
    public Collection<T> findAll() {
        return map.values();
    }

    @NotNull
    @Override
    public Collection<T> findByName(final @NotNull String name) {
        return map.values().stream().filter(e -> name.equals(e.getName())).collect(Collectors.toSet());
    }

    @NotNull
    @Override
    public Collection findByParentId(final @NotNull String id) {
        return map.values().stream().filter(e -> id.equals(e.getParentId())).collect(Collectors.toSet());
    }

    @Override
    public T findOne(final @NotNull String id) {
        return map.values().stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void persist(final @NotNull Entity object) {
        try {
            map.putIfAbsent(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void merge(final @NotNull Entity object) {
        try {
            map.put(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(final @NotNull String id) {
        map.remove(id);
    }

    @Override
    public void remove(final @NotNull Entity object) {
        map.remove(object.getId());
    }

    @Override
    public void removeAll() {
        map.clear();
    }

}
