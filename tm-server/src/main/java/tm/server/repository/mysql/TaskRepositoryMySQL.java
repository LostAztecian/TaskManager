package tm.server.repository.mysql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Task;
import tm.server.api.repository.TaskRepository;

import java.util.Collection;

public class TaskRepositoryMySQL implements TaskRepository {

    @Override @NotNull
    public Collection<Task> findByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return null;
    }

    @Override @NotNull
    public Collection<String> removeByProjectIds(@NotNull final Collection<String> ids) {
        return null;
    }

    @Override @NotNull
    public Collection<Task> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) {
        return null;
    }

    @Override @NotNull
    public Collection<Task> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) {
        return null;
    }

    @Override @NotNull
    public Collection<Task> search(@NotNull final String userId, @NotNull final String searchLine) {
        return null;
    }

    @Override @NotNull
    public Collection<Task> findAll(@NotNull final String userId) {
        return null;
    }

    @Override @NotNull
    public Collection<Task> findByName(@NotNull final String userId, @NotNull final String name) {
        return null;
    }

    @Override @Nullable
    public Task findOne(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Task object) {
        return null;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final Task object) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final Task object) {
        return null;
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) {
        return null;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) {
        return null;
    }
    
}
