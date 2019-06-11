package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.repository.SessionRepository;

import java.util.Collection;

public class SessionRepositoryHibernate implements SessionRepository {
    @Override
    public @NotNull Collection<Session> findAll() throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<Session> findByUserId(@NotNull String userId) throws Exception {
        return null;
    }

    @Override
    public @Nullable Session findById(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean containsId(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull Session session) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean deleteById(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean deleteByUserId(@NotNull String userId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean deleteAll() throws Exception {
        return null;
    }
}
