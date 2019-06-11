package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Task;
import tm.server.api.repository.TaskRepository;

import java.util.Collection;

public class TaskRepositoryHibernate implements TaskRepository {
    @Override
    public @NotNull Collection<Task> findByProjectId(@NotNull String userId, @NotNull String projectId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeByProjectIds(@NotNull String userId, @NotNull Collection<String> ids) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Task> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Task> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Task> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Task> findAll(@NotNull String userId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Task> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull Task object) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull Task object) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull Task object) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        return null;
    }
}
