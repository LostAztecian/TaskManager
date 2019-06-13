package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;

import java.util.Collection;

public interface TaskService extends PlannedEntityService<TaskDTO> {

    @NotNull
    Collection<TaskDTO> getTasksByProjectId(@Nullable SessionDTO session, @Nullable String projectId) throws Exception;

    @NotNull
    Collection<String> removeTasksByProjectIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception;

}
