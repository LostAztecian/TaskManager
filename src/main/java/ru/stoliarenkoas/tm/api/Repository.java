package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface Repository<T extends Entity> {

    @NotNull
    Collection<T> findAll();

    @NotNull
    Collection<T> findByParentId(final @NotNull String id);

    @NotNull
    Collection<T> findByName(final @NotNull String name);

    @Nullable
    T findOne(final @NotNull String id);

    void persist(final @NotNull T object);

    void merge(final @NotNull T object);

    void remove(final @NotNull String id);

    void remove(final @NotNull T object);

    void removeAll();

}
