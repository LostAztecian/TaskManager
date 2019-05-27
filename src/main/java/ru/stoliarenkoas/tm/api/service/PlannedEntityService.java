package ru.stoliarenkoas.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.Collection;

public interface PlannedEntityService<T extends PlannedEntity> extends Service<T> {

    @NotNull
    Collection<T> search(final @Nullable String searchLine);

    @NotNull
    Collection<T> getAllSorted(final @Nullable ComparatorType comparatorType);

    @NotNull
    Collection<T> getAllByNameSorted(final @Nullable String name, final @Nullable ComparatorType comparatorType);

}
