package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Project;
import tm.common.entity.Session;

public interface ProjectService extends PlannedEntityService<Project> {

    @NotNull
    Boolean deleteProjectTasks(@Nullable Session session, @Nullable String projectId) throws Exception;

}
