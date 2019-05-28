package tm.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.entity.PlannedEntity;
import tm.server.api.service.ProjectService;
import tm.server.api.service.TaskService;
import tm.server.api.service.UserService;
import tm.server.entity.User;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @NotNull
    UserService getUserService();

    @NotNull
    ProjectService getProjectService();

    @NotNull
    TaskService getTaskService();

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
