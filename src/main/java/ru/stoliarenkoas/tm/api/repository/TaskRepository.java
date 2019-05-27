package ru.stoliarenkoas.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;

public interface TaskRepository extends PlannedEntityRepository<Task> {

    @NotNull
    Collection<Task> findByProjectId(final @NotNull String userId, final @NotNull String projectId);


}
