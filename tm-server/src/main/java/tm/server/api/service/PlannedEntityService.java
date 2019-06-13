package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.PlannedEntity;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;

import java.util.Collection;

public interface PlannedEntityService<T extends PlannedEntity> extends Service<T> {

    @NotNull
    Collection<T> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception;

    @NotNull
    Collection<T> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception;

    @NotNull
    Collection<T> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception;

}
