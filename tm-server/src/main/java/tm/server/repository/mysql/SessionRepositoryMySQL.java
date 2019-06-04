package tm.server.repository.mysql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.repository.SessionRepository;

import java.util.Collection;

public class SessionRepositoryMySQL implements SessionRepository {
    
    @Override @NotNull
    public Collection<Session> findAll() {
        return null;
    }

    @Override @NotNull
    public Collection<Session> findByUserId(@NotNull final String userId) {
        return null;
    }

    @Override @Nullable
    public Session findById(@NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean containsId(@NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Session session) {
        return null;
    }

    @Override @NotNull
    public Boolean deleteById(@NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean deleteByUserId(@NotNull final String userId) {
        return null;
    }

    @Override @NotNull
    public Boolean deleteAll() {
        return null;
    }
    
}
