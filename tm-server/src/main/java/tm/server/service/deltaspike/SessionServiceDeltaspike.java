package tm.server.service.deltaspike;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.server.annotations.Deltaspike;
import tm.server.api.service.SessionService;
import tm.server.repository.deltaspike.SessionRepositoryDeltaspike;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

@Deltaspike
@Transactional
public class SessionServiceDeltaspike implements SessionService {

    @Inject
    private SessionRepositoryDeltaspike repositoryDeltaspike;

    @Override @NotNull
    public Collection<SessionDTO> getAll() throws Exception {
        return repositoryDeltaspike.findAll();
    }

    @Override @NotNull
    public Collection<SessionDTO> getByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByUserIdEqual(userId);
    }

    @Override @Nullable
    public SessionDTO getById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return null;
        return repositoryDeltaspike.findByIdEqual(id);
    }

    @Override @NotNull
    public Boolean isOpen(@Nullable String id) throws Exception {
        return getById(id) != null;
    }

    @Override @NotNull
    public Boolean open(@Nullable SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null) return false;
        repositoryDeltaspike.persist(sessionDTO);
        return true;
    }

    @Override @NotNull
    public Boolean closeById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return false;
        repositoryDeltaspike.deleteByIdEqual(id);
        return true;
    }

    @Override @NotNull
    public Boolean closeByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return null;
        return repositoryDeltaspike.deleteByUserIdEqual(userId) > 0;
    }

    @Override @NotNull
    public Boolean closeAll() throws Exception {
        repositoryDeltaspike.deleteAll();
        return true;
    }
}
