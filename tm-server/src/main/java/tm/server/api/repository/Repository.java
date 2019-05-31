package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.Entity;

import java.util.Collection;

public interface Repository<T extends Entity> {

    @NotNull
    Collection<T> findAll(@NotNull String userId);

    @NotNull
    Collection<T> findByName(@NotNull String userId, @NotNull String name);

    @Nullable
    T findOne(@NotNull String userId, @NotNull String id);

    @NotNull
    Boolean persist(@NotNull T object);

    @NotNull
    Boolean merge(@NotNull String userId, @NotNull T object);

    @Nullable
    String remove(@NotNull String userId, @NotNull String id);

    @Nullable
    String remove(@NotNull String userId, @NotNull T object);

    @NotNull
    Collection<String> removeByName(@NotNull String userId, @NotNull String name);

    @NotNull
    Collection<String> removeAll(@NotNull String userId);
}
