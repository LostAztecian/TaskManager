package tm.client.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.api.entity.PlannedEntity;
import tm.common.api.webservice.ProjectWebService;
import tm.common.api.webservice.ServerWebService;
import tm.common.api.webservice.TaskWebService;
import tm.common.api.webservice.UserWebService;
import tm.common.entity.User;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);

    @NotNull
    UserWebService getUserService();

    @NotNull
    ProjectWebService getProjectService();

    @NotNull
    TaskWebService getTaskService();

    @NotNull
    ServerWebService getServerService();

    @NotNull
    Comparator<PlannedEntity> getCurrentSortMethod();

    void setCurrentSortMethod(@NotNull Comparator<PlannedEntity> comparator);

    @NotNull
    Map<String, Command> getCommands();

    void init(@Nullable Class[] classes);

    void terminate();

}
