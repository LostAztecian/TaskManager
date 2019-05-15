package ru.stoliarenkoas.tm.api;

import ru.stoliarenkoas.tm.entity.Project;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAll();

    List<Project> findByName();

    Project findById(final String id);

    void removeByName(final String name, boolean allMatches);

    void removeById(final String id);

    void removeAll();

}
