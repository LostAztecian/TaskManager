package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

public class ProjectServiceImpl extends AbstractService<Project> {

    private final Service<Task> taskService;

    public ProjectServiceImpl(Repository<Project> repository, Service<Task> taskService) {
        super(repository);
        this.taskService = taskService;
    }

    @Override
    public void delete(final String id) {
        if (id == null || id.isEmpty()) return;
        taskService.deleteByIds(repository.findOne(id).getTaskIds());
        repository.remove(id);
    }

}
