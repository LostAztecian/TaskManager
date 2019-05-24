package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Collection;

public interface Service<T extends Entity> {

    @NotNull
    Collection<T> getAll();

    @NotNull
    Collection<T> getAllSorted(final @Nullable ComparatorType comparatorType);

    @NotNull
    Collection<T> getAllByName(final @Nullable String name);

    @NotNull
    Collection<T> getAllByNameSorted(final @Nullable String name, final @Nullable ComparatorType comparatorType);

    @NotNull
    Collection<T> search(final @Nullable String searchLine);

    @Nullable
    T get(final @Nullable String id);

    void save(final @Nullable T project);

    void delete(final @Nullable String id);

    void delete(final @Nullable T object);

    void deleteChildrenByParentId(final @Nullable String id);

    void deleteChildrenByParentIds(final @Nullable Collection<String> ids);

    void deleteByIds(final @Nullable Collection<String> ids);

    void deleteByName(final @Nullable String name);

    void deleteAll();

}
