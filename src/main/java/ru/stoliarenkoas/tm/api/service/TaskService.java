package ru.stoliarenkoas.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;

public interface TaskService extends PlannedEntityService<Task> {

    @NotNull
    Collection<Task> getTasksByProjectId(final @Nullable String projectId);

}
