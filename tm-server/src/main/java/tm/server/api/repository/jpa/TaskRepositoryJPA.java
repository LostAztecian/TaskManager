package tm.server.api.repository.jpa;

import org.jetbrains.annotations.NotNull;
import tm.server.api.repository.PlannedEntityRepository;
import tm.server.entity.Task;

import java.util.Collection;

public interface TaskRepositoryJPA extends PlannedEntityRepository<Task> {

    @NotNull
    Collection<Task> findByProjectId(@NotNull String userId, @NotNull String projectId) throws Exception;

    @NotNull
    Collection<String> removeByProjectIds(@NotNull String userId, @NotNull Collection<String> ids) throws Exception;

}
