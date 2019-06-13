package tm.client.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.*;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @Nullable
    SessionDTO getCurrentSession();

    void setCurrentSession(@Nullable SessionDTO user);

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
