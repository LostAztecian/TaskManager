package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.entity.Task;

public class TaskServiceImpl extends AbstractService<Task> {

    public TaskServiceImpl(final Repository<Task> repository) {
        super(repository, null);
    }

}
