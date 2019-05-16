package ru.stoliarenkoas.tm.api;

import ru.stoliarenkoas.tm.entity.Project;

import java.util.Collection;

public interface ProjectRepository {

    Collection<Project> findAll();

    Collection<Project> findByName(final String name);

    Project findOne(final String id);

    void persist(final Project project);

    void merge(final Project project);

    void remove(final String id);

    void removeByName(final String name, boolean allMatches);

    void removeAll();

}
