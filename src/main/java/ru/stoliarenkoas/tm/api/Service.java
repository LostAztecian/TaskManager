package ru.stoliarenkoas.tm.api;

import java.util.Collection;

public interface Service<T extends Entity> {

    Collection<T> getAll();

    Collection<T> getAllByName(String name);

    Collection<T> getByIds(Collection<String> ids);

    T get(String id);

    void save(T project);

    void delete(String id);

    void deleteByIds(Collection<String> ids);

    void deleteByName(String name, boolean allMatches);

    void deleteAll();

}
