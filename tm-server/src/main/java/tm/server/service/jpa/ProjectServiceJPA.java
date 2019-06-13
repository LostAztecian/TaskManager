package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.server.api.service.ProjectService;

import java.util.Collection;

public class ProjectServiceJPA implements ProjectService {

    @Override
    public @NotNull Boolean deleteProjectTasks(@Nullable SessionDTO session, @Nullable String projectId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> getAll(@Nullable SessionDTO session) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public ProjectDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        return null;
    }

    @Override
    public Boolean save(@Nullable SessionDTO session, @Nullable ProjectDTO object) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable ProjectDTO object) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteAll(@Nullable SessionDTO session) throws Exception {
        return null;
    }
}
