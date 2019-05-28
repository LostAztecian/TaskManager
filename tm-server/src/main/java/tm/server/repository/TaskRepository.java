package tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.entity.Task;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository extends AbstractRepository<Task> implements tm.server.api.repository.TaskRepository {

    @Override @NotNull
    public Collection<Task> findByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return findAll(userId).stream().filter(t -> projectId.equals(t.getProjectId())).collect(Collectors.toSet());
    }

    @Override @NotNull
    public Collection<Task> search(@NotNull final String userId, @Nullable final String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }
}
