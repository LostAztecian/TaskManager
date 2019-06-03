package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;

import java.util.Collection;

public interface SessionService {

    @NotNull
    Collection<Session> getAll();

    @NotNull
    Collection<Session> getByUserId(@Nullable String userId);

    @Nullable
    Session getById(@Nullable String id);

    @NotNull
    Boolean isOpen(@Nullable String id);

    @NotNull
    Boolean open(@Nullable Session session);

    @NotNull
    Boolean closeById(@Nullable String id);

    @NotNull
    Boolean closeByUserId(@Nullable String userId);

    @NotNull
    Boolean closeAll();

}
