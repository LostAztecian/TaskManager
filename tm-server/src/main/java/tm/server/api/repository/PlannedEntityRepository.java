package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.PlannedEntity;
import tm.common.comparator.ComparatorType;

import java.util.Collection;

public interface PlannedEntityRepository<T extends PlannedEntity> extends Repository<T> {

    @NotNull
    Collection<T> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType);

    @NotNull
    Collection<T> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name);

    @NotNull
    Collection<T> search(@NotNull String userId, @NotNull String searchLine);
}
