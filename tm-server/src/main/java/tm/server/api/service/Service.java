package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.entity.Entity;

import java.util.Collection;

public interface Service<T extends Entity> {

    @NotNull
    Collection<T> getAll();

    @NotNull
    Collection<T> getAllByName(@Nullable String name);

    @Nullable
    T get(@Nullable String id);

    Boolean save(@Nullable T object);

    Boolean delete(@Nullable String id);

    Boolean delete(@Nullable T object);

    Boolean deleteByIds(@Nullable Collection<String> ids);

    Boolean deleteByName(@Nullable String name);

    Boolean deleteChildrenByParentId(@Nullable String id);

    Boolean deleteChildrenByParentIds(@Nullable Collection<String> ids);

    Boolean deleteAll();

}
