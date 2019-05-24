package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.*;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskServiceImpl extends AbstractService<Task> implements TaskService {

    public TaskServiceImpl(final @NotNull Repository<Task> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    protected @Nullable Service<? extends Entity> getChildService() {
        return null;
    }

    public @NotNull Collection<Task> getTasksByProjectId(final @Nullable String projectId) {
        if (projectId == null) return Collections.emptySet();
        return getAll().stream().filter(t -> projectId.equals(t.getProjectId())).collect(Collectors.toSet());
    }

}
