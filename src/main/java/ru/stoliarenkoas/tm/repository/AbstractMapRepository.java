package ru.stoliarenkoas.tm.repository;

import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMapRepository<T extends Entity> implements Repository {

    protected final Map<String, T> map = new LinkedHashMap<>();

    @Override
    public Collection<T> findAll() {
        return map.values();
    }

    @Override
    public Collection<T> findByName(final String name) {
        return map.values().stream().filter(e -> e.getName().equals(name)).collect(Collectors.toSet());
    }

    @Override
    public Collection findByParentId(String id) {
        return map.values().stream().filter(e -> e.getParentId().equals(id)).collect(Collectors.toSet());
    }

    @Override
    public T findOne(String id) {
        return map.values().stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void persist(final Entity object) {
        try {
            map.putIfAbsent(object.getId(), (T)object);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void merge(final Entity object) {
        try {
            map.put(object.getId(), (T)object);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

//    public void removeByName(final String name, boolean allMatches) {
//        Stream<T> stream = map.values().stream().filter(t -> t.getName().equals(name));
//        if (!allMatches) stream = stream.limit(1);
//        stream.forEachOrdered(t -> map.remove(t.getName()));
//    }

    @Override
    public void remove(final String id) {
        map.remove(id);
    }

    @Override
    public void remove(Entity object) {
        map.remove(object.getId());
    }

    @Override
    public void removeAll() {
        map.clear();
    }

}
