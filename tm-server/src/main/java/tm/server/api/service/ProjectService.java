package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;

public interface ProjectService extends PlannedEntityService<ProjectDTO> {

    @NotNull
    Boolean deleteProjectTasks(@Nullable SessionDTO session, @Nullable String projectId) throws Exception;

}
