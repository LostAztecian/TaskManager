package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.TaskRepository;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

public class TaskService {

    private TaskRepository repository;

    public Collection<Task> getAllTasks() {
        return repository.findAll();
    }

    public Collection<Task> getAllByName(String name) {
        if (name == null || name.isEmpty()) return Collections.EMPTY_SET;
        return repository.findByName(name);
    }

    public Collection<Task> getTasksByIds(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.EMPTY_SET;
        final Collection<Task> tasks = new LinkedHashSet<>();
        ids.forEach(id -> Optional.of(this.getTask(id)).ifPresent(tasks::add));
        return tasks;
    }

    public Task getTask(String id) {
        if (id == null || id.isEmpty()) return null;
        return repository.findOne(id);
    }

    public void saveTask(Task task) {
        if (task == null || task.getId() == null || task.getId().isEmpty()) return;
        repository.merge(task);
    }

    public void deleteTask(String id) {
        if (id == null || id.isEmpty()) return;
        repository.remove(id);
    }

    public void deleteTaskWithName(String name, boolean allMatches) {
        if (name == null || name.isEmpty()) return;
        repository.removeByName(name, allMatches);
    }

    public void deleteAll() {
        repository.removeAll();
    }
}
