package ru.stoliarenkoas.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.Entity;

import java.util.Collection;

public interface Service<T extends Entity> {

    @NotNull
    Collection<T> getAll();

    @NotNull
    Collection<T> getAllByName(final @Nullable String name);

    @Nullable
    T get(final @Nullable String id);

    void save(final @Nullable T project);

    void delete(final @Nullable String id);

    void delete(final @Nullable T object);

    void deleteByIds(final @Nullable Collection<String> ids);

    void deleteByName(final @Nullable String name);

    void deleteChildrenByParentId(final @Nullable String id);

    void deleteChildrenByParentIds(final @Nullable Collection<String> ids);

    void deleteAll();

}
