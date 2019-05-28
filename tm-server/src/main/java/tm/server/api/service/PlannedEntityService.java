package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.entity.PlannedEntity;
import tm.server.comparator.ComparatorType;

import java.util.Collection;

public interface PlannedEntityService<T extends PlannedEntity> extends Service<T> {

    @NotNull
    Collection<T> search(@Nullable String searchLine);

    @NotNull
    Collection<T> getAllSorted(@Nullable ComparatorType comparatorType);

    @NotNull
    Collection<T> getAllByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType);

}
