package ru.stoliarenkoas.tm.repository;

import ru.stoliarenkoas.tm.api.TaskRepository;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskMapRepository implements TaskRepository {

    private final Map<String, Task> map = new LinkedHashMap<>();

    @Override
    public Collection<Task> findAll() {
        return map.values();
    }

    @Override
    public Collection<Task> findByName(String name) {
        return map.values().stream().filter(t -> t.getName().equals(name)).collect(Collectors.toSet());
    }

    @Override
    public Task findOne(String id) {
        return map.values().stream().filter(t -> t.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void persist(Task task) {
        map.putIfAbsent(task.getId(), task);
    }

    @Override
    public void merge(Task task) {
        map.put(task.getId(), task);
    }

    @Override
    public void removeByName(String name, boolean allMatches) {
        Stream<Task> stream = map.values().stream().filter(t -> t.getName().equals(name));
        if (!allMatches) stream = stream.limit(1);
        stream.forEachOrdered(t -> map.remove(t.getName()));
    }

    @Override
    public void remove(String id) {
        map.remove(id);
    }

    @Override
    public void removeAll() {
        map.clear();
    }

}
