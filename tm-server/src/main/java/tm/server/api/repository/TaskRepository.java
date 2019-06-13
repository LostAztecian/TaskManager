package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.TaskDTO;

import java.util.Collection;

public interface TaskRepository extends PlannedEntityRepository<TaskDTO> {

    @NotNull
    Collection<TaskDTO> findByProjectId(@NotNull String userId, @NotNull String projectId) throws Exception;

    @NotNull
    Collection<String> removeByProjectIds(@NotNull String userId, @NotNull Collection<String> ids) throws Exception;


}
