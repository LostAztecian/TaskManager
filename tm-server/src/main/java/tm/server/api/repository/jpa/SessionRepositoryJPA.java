package tm.server.api.repository.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.entity.Session;

import java.util.Collection;

public interface SessionRepositoryJPA {

    @NotNull
    Collection<Session> findAll() throws Exception;

    @NotNull
    Collection<Session> findByUserId(@NotNull String userId) throws Exception;

    @Nullable
    Session findById(@NotNull String id) throws Exception;

    @NotNull
    Boolean containsId(@NotNull String id) throws Exception;

    @NotNull
    Boolean persist(@NotNull Session session) throws Exception;

    @NotNull
    Boolean deleteById(@NotNull String id) throws Exception;

    @NotNull
    Boolean deleteByUserId(@NotNull String userId) throws Exception;

    @NotNull
    Boolean deleteAll() throws Exception;
    
}
