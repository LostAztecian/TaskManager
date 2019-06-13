package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;

import java.util.Collection;

public interface SessionRepository {

    @NotNull
    Collection<SessionDTO> findAll() throws Exception;

    @NotNull
    Collection<SessionDTO> findByUserId(@NotNull String userId) throws Exception;

    @Nullable
    SessionDTO findById(@NotNull String id) throws Exception;

    @NotNull
    Boolean containsId(@NotNull String id) throws Exception;

    @NotNull
    Boolean persist(@NotNull SessionDTO session) throws Exception;

    @NotNull
    Boolean deleteById(@NotNull String id) throws Exception;

    @NotNull
    Boolean deleteByUserId(@NotNull String userId) throws Exception;

    @NotNull
    Boolean deleteAll() throws Exception;

}
