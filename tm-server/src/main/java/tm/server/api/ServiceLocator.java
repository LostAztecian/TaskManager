package tm.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.Command;
import tm.common.api.entity.PlannedEntity;
import tm.common.entity.SessionDTO;
import tm.server.api.service.*;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
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

    @NotNull
    SessionService getSessionService();

    @Nullable
    SessionDTO getCurrentSession();

    void setCurrentSession(@Nullable SessionDTO session);

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
