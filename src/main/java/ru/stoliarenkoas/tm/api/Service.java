package ru.stoliarenkoas.tm.api;

import java.util.Collection;

public interface Service<T extends Entity> {

    Collection<T> getAll();

    Collection<T> getAllByName(String name);

    Collection<T> getByIds(Collection<String> ids);

    Collection<T> getAllByParentId(final String id);

    T get(String id);

    void save(T project);

    void delete(String id);

    void delete(T object);

    void deleteChildrenByParentId(final String id);

    void deleteByIds(Collection<String> ids);

    void deleteByName(String name, boolean allMatches);

    void deleteAll();

}
