package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;

import java.util.Collection;

public interface SessionRepository {

    @NotNull
    Collection<Session> findAll();

    @NotNull
    Collection<Session> findByUserId(@NotNull String userId);

    @Nullable
    Session findById(@NotNull String id);

    @NotNull
    Boolean containsId(@NotNull String id);

    @NotNull
    Boolean persist(@NotNull Session session);

    @NotNull
    Boolean deleteById(@NotNull String id);

    @NotNull
    Boolean deleteByUserId(@NotNull String userId);

    @NotNull
    Boolean deleteAll();

}
