package tm.server.repository.mysql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.server.api.repository.ProjectRepository;

import java.util.Collection;

public class ProjectRepositoryMySQL implements ProjectRepository {

    @Override @NotNull
    public Collection<Project> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) {
        return null;
    }

    @Override @NotNull
    public Collection<Project> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) {
        return null;
    }

    @Override @NotNull
    public Collection<Project> search(@NotNull final String userId, @NotNull final String searchLine) {
        return null;
    }

    @Override @NotNull
    public Collection<Project> findAll(@NotNull final String userId) {
        return null;
    }

    @Override @NotNull
    public Collection<Project> findByName(@NotNull final String userId, @NotNull final String name) {
        return null;
    }

    @Override @Nullable
    public Project findOne(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Project object) {
        return null;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final Project object) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final Project object) {
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
