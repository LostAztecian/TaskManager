package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;

public class ProjectServiceImpl extends AbstractService<Project> {

    public ProjectServiceImpl(final @NotNull Repository<Project> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    protected @Nullable Service<? extends Entity> getChildService() {
        return serviceLocator.getTaskService();
    }

}
