package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.Entity;
import tm.common.entity.Session;

import java.util.Collection;

public interface Service<T extends Entity> {

    @NotNull
    Collection<T> getAll(@Nullable Session session);

    @NotNull
    Collection<T> getAllByName(@Nullable Session session, @Nullable String name);

    @Nullable
    T get(@Nullable Session session, @Nullable String id);

    Boolean save(@Nullable Session session, @Nullable T object);

    Boolean delete(@Nullable Session session, @Nullable String id);

    Boolean delete(@Nullable Session session, @Nullable T object);

    Boolean deleteByIds(@Nullable Session session, @Nullable Collection<String> ids);

    Boolean deleteByName(@Nullable Session session, @Nullable String name);

    Boolean deleteAll(@Nullable Session session);

}
