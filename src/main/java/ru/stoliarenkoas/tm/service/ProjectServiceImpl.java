package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.entity.Project;

public class ProjectServiceImpl extends AbstractService<Project> {

    public ProjectServiceImpl(final Repository<Project> repository, final TaskServiceImpl taskService) {
        super(repository, taskService);
    }

}
