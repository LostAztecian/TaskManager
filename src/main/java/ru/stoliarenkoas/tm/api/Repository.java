package ru.stoliarenkoas.tm.api;

import java.util.Collection;

public interface Repository<T extends Entity> {

    Collection<T> findAll();

    Collection<T> findByName(final String name);

    T findOne(final String id);

    void persist(final T object);

    void merge(final T object);

    void remove(final String id);

//    void removeByName(final String name, boolean allMatches);

    void removeAll();

}
