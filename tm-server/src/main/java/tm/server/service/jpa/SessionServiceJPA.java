package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.server.api.service.SessionService;

import java.util.Collection;

public class SessionServiceJPA implements SessionService {

    @Override
    public @NotNull Collection<SessionDTO> getAll() throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<SessionDTO> getByUserId(@Nullable String userId) throws Exception {
        return null;
    }

    @Override
    public @Nullable SessionDTO getById(@Nullable String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean isOpen(@Nullable String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean open(@Nullable SessionDTO session) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean closeById(@Nullable String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean closeByUserId(@Nullable String userId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean closeAll() throws Exception {
        return null;
    }
}
