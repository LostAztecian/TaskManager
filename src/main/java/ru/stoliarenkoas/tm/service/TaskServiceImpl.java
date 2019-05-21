package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

public class TaskServiceImpl extends AbstractService<Task> {

    public TaskServiceImpl(final @NotNull Repository<Task> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    protected @Nullable String getUserId(@NotNull Task object) {
        final Project project = serviceLocator.getProjectService().get(object.getParentId());
        if (project == null) return null;
        return project.getParentId();
    }

    @Override
    protected @Nullable Service<? extends Entity> getChildService() {
        return null;
    }
}
