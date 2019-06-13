package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;

import java.util.Collection;

public interface SessionService {

    @NotNull
    Collection<SessionDTO> getAll() throws Exception;

    @NotNull
    Collection<SessionDTO> getByUserId(@Nullable String userId) throws Exception;

    @Nullable
    SessionDTO getById(@Nullable String id) throws Exception;

    @NotNull
    Boolean isOpen(@Nullable String id) throws Exception;

    @NotNull
    Boolean open(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean closeById(@Nullable String id) throws Exception;

    @NotNull
    Boolean closeByUserId(@Nullable String userId) throws Exception;

    @NotNull
    Boolean closeAll() throws Exception;

}
