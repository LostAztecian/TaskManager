package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.SessionRepository;
import tm.server.api.service.SessionService;

import java.util.Collection;
import java.util.Collections;

public class SessionServiceImpl implements SessionService {
    
    private final SessionRepository repository;
    private final ServiceLocator serviceLocator;

    public SessionServiceImpl(@NotNull final SessionRepository repository, @NotNull final ServiceLocator serviceLocator) {
        this.repository = repository;
        this.serviceLocator = serviceLocator;
    }

    @Override @NotNull
    public Collection<Session> getAll() {
        return repository.findAll();
    }

    @Override @NotNull
    public Collection<Session> getByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptySet();
        return repository.findByUserId(userId);
    }

    @Override @Nullable
    public Session getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return repository.findById(id);
    }

    @Override @NotNull
    public Boolean isOpen(@Nullable String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.containsId(id);
    }

    @Override @NotNull
    public Boolean open(@Nullable final Session session) {
        if (session == null) return false;
        return repository.persist(session);
    }

    @Override @NotNull
    public Boolean closeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.deleteById(id);
    }

    @Override @NotNull
    public Boolean closeByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return false;
        return repository.deleteByUserId(userId);
    }

    @Override @NotNull
    public Boolean closeAll() {
        return repository.deleteAll();
    }
}
