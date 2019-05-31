package tm.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.api.entity.PlannedEntity;
import tm.common.entity.User;
import tm.server.api.service.ProjectService;
import tm.server.api.service.ServerService;
import tm.server.api.service.TaskService;
import tm.server.api.service.UserService;

import javax.xml.ws.Endpoint;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @NotNull
    UserService getUserService();

    @NotNull
    ProjectService getProjectService();

    @NotNull
    TaskService getTaskService();

    @NotNull
    ServerService getServerService();

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);

    @NotNull
    Comparator<PlannedEntity> getCurrentSortMethod();

    void setCurrentSortMethod(@NotNull Comparator<PlannedEntity> comparator);

    @NotNull
    Map<String, Command> getCommands();

    @NotNull
    Collection<Endpoint> getEndpoints();

    void init(@Nullable Class[] classes);

    void terminate();

}
