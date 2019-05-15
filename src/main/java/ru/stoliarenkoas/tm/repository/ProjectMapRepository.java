package ru.stoliarenkoas.tm.repository;

import ru.stoliarenkoas.tm.api.ProjectRepository;
import ru.stoliarenkoas.tm.entity.Project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectMapRepository implements ProjectRepository {

    private final Map<String, Project> map = new LinkedHashMap<>();

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public List<Project> findByName() {
        return null;
    }

    @Override
    public Project findById(String id) {
        return null;
    }

    @Override
    public void removeByName(String name, boolean allMatches) {

    }

    @Override
    public void removeById(String id) {

    }

    @Override
    public void removeAll() {

    }
}
