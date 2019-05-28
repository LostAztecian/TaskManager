package ru.stoliarenkoas.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.comparator.ComparatorType;

import java.util.Collection;

public interface PlannedEntityRepository<T extends PlannedEntity> extends Repository<T> {

    @NotNull
    Collection<T> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType);

    @NotNull
    Collection<T> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull String name);

    @NotNull
    Collection<T> search(@NotNull final String userId, @Nullable final String searchLine);
}
