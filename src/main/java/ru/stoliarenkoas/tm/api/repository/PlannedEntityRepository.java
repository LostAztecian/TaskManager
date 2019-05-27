package ru.stoliarenkoas.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Collection;

public interface PlannedEntityRepository<T extends PlannedEntity> extends Repository<T> {

    @NotNull
    Collection<T> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType);

    @NotNull
    Collection<T> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name);

    @NotNull
    Collection<T> search(final @NotNull String userId, final @Nullable String searchLine);
}
