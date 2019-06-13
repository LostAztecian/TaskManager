package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.service.TaskService;

import java.util.Collection;

public class TaskServiceJPA implements TaskService {

    @Override
    public @NotNull Collection<TaskDTO> getTasksByProjectId(@Nullable SessionDTO session, @Nullable String projectId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeTasksByProjectIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> getAll(@Nullable SessionDTO session) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public TaskDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        return null;
    }

    @Override
    public Boolean save(@Nullable SessionDTO session, @Nullable TaskDTO object) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable TaskDTO object) throws Exception {
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
