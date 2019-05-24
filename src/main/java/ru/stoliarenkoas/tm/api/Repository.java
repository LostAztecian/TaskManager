package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Collection;

public interface Repository<T extends Entity> {

    @NotNull
    Collection<T> findAll(final @NotNull String userId);

    @NotNull
    Collection<T> findAllAndSort(final @NotNull String userId, final @NotNull ComparatorType comparatorType);

    @NotNull
    Collection<T> findByName(final @NotNull String userId, final @NotNull String name);

    @NotNull
    Collection<T> findByNameAndSort(final @NotNull String userId, final @NotNull ComparatorType comparatorType, final @NotNull String name);

    @NotNull
    Collection<T> search(final @NotNull String userId, final @Nullable String searchLine);

    @Nullable
    T findOne(final @NotNull String userId, final @NotNull String id);

    void persist(final @NotNull T object);

    void merge(final @NotNull String userId, final @NotNull T object);

    @Nullable
    String remove(final @NotNull String userId, final @NotNull String id);

    @Nullable
    String remove(final @NotNull String userId, final @NotNull T object);

    @NotNull
    Collection<String> removeByName(final @NotNull String userId, final @NotNull String name);

    @NotNull
    Collection<String> removeAll(final @NotNull String userId);

}
