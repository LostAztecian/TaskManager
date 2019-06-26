package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.common.entity.SessionDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.service.SessionService;
import tm.server.entity.Session;
import tm.server.entity.User;
import tm.server.repository.SessionRepositorySpring;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("spring")
public class SessionServiceSpring implements SessionService {

    @Autowired
    private SessionRepositorySpring repositoryDeltaspike;

    @Autowired
    private ServiceLocator serviceLocator;

    @Override @NotNull
    public Collection<SessionDTO> getAll() throws Exception {
        return ((Collection<Session>)repositoryDeltaspike.findAll()).stream().map(Session::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<SessionDTO> getByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        return repositoryDeltaspike.findByUser_Id(userId).stream().map(Session::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public SessionDTO getById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return null;
        final Optional<Session> session = repositoryDeltaspike.findById(id);
        return session.map(Session::toDTO).orElse(null);
    }

    @Override @NotNull
    public Boolean isOpen(@Nullable String id) throws Exception {
        return getById(id) != null;
    }

    @Override @NotNull
    public Boolean open(@Nullable SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null) return false;
        repositoryDeltaspike.save(new Session(sessionDTO, new User(serviceLocator.getUserService().get(sessionDTO, sessionDTO.getUserId()))));
        return true;
    }

    @Override @NotNull
    public Boolean closeById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return false;
        repositoryDeltaspike.deleteById(id);
        return true;
    }

    @Override @NotNull
    public Boolean closeByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return null;
        return repositoryDeltaspike.deleteByUser_Id(userId) > 0;
    }

    @Override @NotNull
    public Boolean closeAll() throws Exception {
        repositoryDeltaspike.deleteAll();
        return true;
    }
}
