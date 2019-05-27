package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.repository.TaskRepository;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskMapRepository extends AbstractMapRepository<Task> implements TaskRepository {

    @Override
    public @NotNull Collection<Task> findByProjectId(@NotNull String userId, @NotNull String projectId) {
        return findAll(userId).stream().filter(t -> projectId.equals(t.getProjectId())).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<Task> search(@NotNull String userId, @Nullable String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }
}
