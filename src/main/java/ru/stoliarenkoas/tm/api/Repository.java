package ru.stoliarenkoas.tm.api;

import java.util.Collection;

public interface Repository<T extends Entity> {

    Collection<T> findAll();

    Collection<T> findByParentId(final String id);

    Collection<T> findByName(final String name);

    T findOne(final String id);

    void persist(final T object);

    void merge(final T object);

    void remove(final String id);

    void remove(final T object);

//    void removeByName(final String name, boolean allMatches);

    void removeAll();

}
