package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.Entity;
import tm.common.entity.SessionDTO;

import java.util.Collection;

public interface Service<T extends Entity> {

    @NotNull
    Collection<T> getAll(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Collection<T> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception;

    @Nullable
    T get(@Nullable SessionDTO session, @Nullable String id) throws Exception;

    Boolean save(@Nullable SessionDTO session, @Nullable T object) throws Exception;

    Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception;

    Boolean delete(@Nullable SessionDTO session, @Nullable T object) throws Exception;

    Boolean deleteByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception;

    Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception;

    Boolean deleteAll(@Nullable SessionDTO session) throws Exception;

}
