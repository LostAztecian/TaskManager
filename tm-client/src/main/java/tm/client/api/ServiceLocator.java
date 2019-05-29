package tm.client.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.api.entity.PlannedEntity;
import tm.client.entity.User;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @Nullable
    User getCurrentUser();

    void setCurrentUser(final @Nullable User user);

    @NotNull
    Comparator<PlannedEntity> getCurrentSortMethod();

    void setCurrentSortMethod(final @NotNull Comparator<PlannedEntity> comparator);

    @NotNull
    Map<String, Command> getCommands();

    void init(final @Nullable Class[] classes);

    void terminate();

}
