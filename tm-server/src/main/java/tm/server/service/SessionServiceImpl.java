package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.SessionRepository;
import tm.server.api.service.SessionService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;

public class SessionServiceImpl implements SessionService {
    
    private final SessionRepository repository;
    private final ServiceLocator serviceLocator;
    private final Connection connection;

    public SessionServiceImpl(@NotNull final SessionRepository repository, @NotNull final ServiceLocator serviceLocator) {
        this.repository = repository;
        this.serviceLocator = serviceLocator;
        this.connection = serviceLocator.getDatabaseConnection();
    }

    @Override @NotNull
    public Collection<SessionDTO> getAll() throws Exception {
        return repository.findAll();
    }

    @Override @NotNull
    public Collection<SessionDTO> getByUserId(@Nullable final String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return Collections.emptySet();
        return repository.findByUserId(userId);
    }

    @Override @Nullable
    public SessionDTO getById(@Nullable final String id) throws Exception {
        if (id == null || id.isEmpty()) return null;
        return repository.findById(id);
    }

    @Override @NotNull
    public Boolean isOpen(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return false;
        return repository.containsId(id);
    }

    @Override @NotNull
    public Boolean open(@Nullable final SessionDTO session) throws Exception {
        try {
            if (session == null) return false;
            final Boolean result = repository.persist(session);
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeById(@Nullable final String id) throws Exception {
        try {
            if (id == null || id.isEmpty()) return false;
            final Boolean result =  repository.deleteById(id);
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeByUserId(@Nullable final String userId) throws Exception {
        try {
            if (userId == null || userId.isEmpty()) return false;
            final Boolean result =  repository.deleteByUserId(userId);
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeAll() throws Exception {
        try {
            final Boolean result =  repository.deleteAll();
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
}
