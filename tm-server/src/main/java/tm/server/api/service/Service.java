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

    void save(@Nullable T project);

    void delete(@Nullable String id);

    void delete(@Nullable T object);

    void deleteByIds(@Nullable Collection<String> ids);

    void deleteByName(@Nullable String name);

    void deleteChildrenByParentId(@Nullable String id);

    void deleteChildrenByParentIds(@Nullable Collection<String> ids);

    void deleteAll();

}
