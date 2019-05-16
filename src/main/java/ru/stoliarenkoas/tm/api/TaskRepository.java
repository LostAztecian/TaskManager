package ru.stoliarenkoas.tm.api;

import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;

public interface TaskRepository {

    Collection<Task> findAll();

    Collection<Task> findByName(final String name);

    Task findOne(final String id);

    void persist(final Task task);

    void merge(final Task task);

    void remove(final String id);

    void removeByName(final String name, boolean allMatches);

    void removeAll();

}
