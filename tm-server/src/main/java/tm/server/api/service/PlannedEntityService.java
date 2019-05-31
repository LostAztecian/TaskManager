package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.PlannedEntity;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;

import java.util.Collection;

public interface PlannedEntityService<T extends PlannedEntity> extends Service<T> {

    @NotNull
    Collection<T> search(@Nullable Session session, @Nullable String searchLine);

    @NotNull
    Collection<T> getAllSorted(@Nullable Session session, @Nullable ComparatorType comparatorType);

    @NotNull
    Collection<T> getAllByNameSorted(@Nullable Session session, @Nullable String name, @Nullable ComparatorType comparatorType);

}
