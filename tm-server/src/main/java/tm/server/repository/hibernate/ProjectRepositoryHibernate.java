package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.server.api.repository.ProjectRepository;

import java.util.Collection;

public class ProjectRepositoryHibernate implements ProjectRepository {
    @Override
    public @NotNull Collection<Project> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Project> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Project> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Project> findAll(@NotNull String userId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Project> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public Project findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull Project object) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull Project object) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull Project object) throws Exception {
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
