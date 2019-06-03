package tm.client.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.api.Command;
import tm.client.api.PlannedEntity;
import tm.common.api.webservice.ProjectService;
import tm.common.api.webservice.ServerService;
import tm.common.api.webservice.TaskService;
import tm.common.api.webservice.UserService;
import tm.common.api.webservice.Session;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @Nullable
    Session getCurrentSession();

    void setCurrentSession(@Nullable Session user);

    @NotNull
    UserService getUserService();

    @NotNull
    ProjectService getProjectService();

    @NotNull
    TaskService getTaskService();

    @NotNull
    ServerService getServerService();

    @NotNull
    Comparator<PlannedEntity> getCurrentSortMethod();

    void setCurrentSortMethod(@NotNull Comparator<PlannedEntity> comparator);

    @NotNull
    Map<String, Command> getCommands();

    void init(@Nullable Class[] classes);

    void terminate();

}
