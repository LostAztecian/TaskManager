package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.entity.Task;

public class TaskServiceImpl extends AbstractService<Task> {

    public TaskServiceImpl(Repository<Task> repository) {
        super(repository);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) return;
        repository.remove(id);
    }

}
