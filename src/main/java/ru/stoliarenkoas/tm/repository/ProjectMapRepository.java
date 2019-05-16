package ru.stoliarenkoas.tm.repository;

import ru.stoliarenkoas.tm.api.ProjectRepository;
import ru.stoliarenkoas.tm.entity.Project;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectMapRepository implements ProjectRepository {

    private final Map<String, Project> map = new LinkedHashMap<>();

    @Override
    public Collection<Project> findAll() {
        return map.values();
    }

    @Override
    public Collection<Project> findByName(final String name) {
        return map.values().stream().filter(p -> p.getName().equals(name)).collect(Collectors.toSet());
    }

    @Override
    public Project findOne(String id) {
        return map.values().stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void persist(Project project) {
        map.putIfAbsent(project.getId(), project);
    }

    @Override
    public void merge(Project project) {
        map.put(project.getId(), project);
    }

    @Override
    public void removeByName(String name, boolean allMatches) {
        Stream<Project> stream = map.values().stream().filter(p -> p.getName().equals(name));
        if (!allMatches) stream = stream.limit(1);
        stream.forEachOrdered(p -> map.remove(p.getName()));
    }

    @Override
    public void remove(final String id) {
        map.remove(id);
    }

    @Override
    public void removeAll() {
        map.clear();
    }

}
