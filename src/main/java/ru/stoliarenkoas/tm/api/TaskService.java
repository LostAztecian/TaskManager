package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;

public interface TaskService extends Service<Task> {

    @NotNull Collection<Task> getTasksByProjectId(final @Nullable String projectId);

}
