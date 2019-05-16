package ru.stoliarenkoas.tm.service;

import lombok.RequiredArgsConstructor;
import ru.stoliarenkoas.tm.api.TaskRepository;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Collection<Task> getAll() {
        return repository.findAll();
    }

    public Collection<Task> getAllByName(String name) {
        if (name == null || name.isEmpty()) return Collections.EMPTY_SET;
        return repository.findByName(name);
    }

    public Collection<Task> getByIds(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.EMPTY_SET;
        final Collection<Task> tasks = new LinkedHashSet<>();
        ids.forEach(id -> Optional.of(this.get(id)).ifPresent(tasks::add));
        return tasks;
    }

    public Task get(String id) {
        if (id == null || id.isEmpty()) return null;
        return repository.findOne(id);
    }

    public void save(Task task) {
        if (task == null || task.getId() == null || task.getId().isEmpty()) return;
        repository.merge(task);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) return;
        repository.remove(id);
    }

    public void deleteByName(String name, boolean allMatches) {
        if (name == null || name.isEmpty()) return;
        repository.removeByName(name, allMatches);
    }

    public void deleteByIds(final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        ids.forEach(this::delete);
    }

    public void deleteAll() {
        repository.removeAll();
    }
}
