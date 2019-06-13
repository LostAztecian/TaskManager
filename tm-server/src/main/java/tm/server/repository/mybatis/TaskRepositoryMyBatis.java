package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.TaskDTO;
import tm.server.api.repository.TaskRepository;
import tm.server.repository.mybatis.mapper.TaskMapper;
import tm.server.utils.DatabaseUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskRepositoryMyBatis implements TaskRepository {

    private final TaskMapper mapper;

    public TaskRepositoryMyBatis(@NotNull final SqlSession sqlSession) {
        this.mapper = sqlSession.getMapper(TaskMapper.class);
    }

    @Override
    public @NotNull Collection<TaskDTO> findAll(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @NotNull Collection<TaskDTO> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        final String sortColumn = DatabaseUtil.getSortColumn(comparatorType);
        return mapper.findByUserIdAndSort(userId, sortColumn);
    }

    @Override
    public @NotNull Collection<TaskDTO> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return mapper.findByName(userId, name);
    }

    @Override
    public @NotNull Collection<TaskDTO> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        final String sortColumn = DatabaseUtil.getSortColumn(comparatorType);
        return mapper.findByNameAndSort(userId, name, sortColumn);
    }

    @Override
    public @NotNull Collection<TaskDTO> findByProjectId(@NotNull String userId, @NotNull String projectId) throws Exception {
        return mapper.findByProjectId(userId, projectId);
    }

    @Nullable
    @Override
    public TaskDTO findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return mapper.findOne(userId, id);
    }

    @Override
    public @NotNull Collection<TaskDTO> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return mapper.search(userId, searchLine);
    }

    @Override
    public @NotNull Boolean persist(@NotNull TaskDTO task) throws Exception {
        mapper.persist(task);
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull TaskDTO task) throws Exception {
        final TaskDTO oldTask = mapper.findOne(userId, task.getId());
        if (oldTask != null) mapper.removeById(userId, task.getId());
        mapper.persist(task);
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final TaskDTO deletedTask = mapper.findOne(userId, id);
        if (deletedTask == null) return null;
        mapper.removeById(userId, deletedTask.getId());
        return deletedTask.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull TaskDTO task) throws Exception {
        return remove(userId, task.getId());
    }

    @Override
    public @NotNull Collection<String> removeByProjectIds(@NotNull String userId, @NotNull Collection<String> ids) throws Exception {
        final Collection<TaskDTO> deletedTasks = new ArrayList<>();
        for (final String id : ids) {
            deletedTasks.addAll(mapper.findByProjectId(userId, id));
            mapper.removeByProjectId(userId, id);
        }
        return deletedTasks.stream().map(TaskDTO::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final Collection<TaskDTO> deletedTasks = mapper.findByName(userId, name);
        if (deletedTasks.isEmpty()) return Collections.emptySet();
        mapper.removeByName(userId, name);
        return deletedTasks.stream().map(TaskDTO::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final Collection<TaskDTO> deletedTasks = findAll(userId);
        if (deletedTasks.isEmpty()) return Collections.emptySet();
        mapper.clear(userId);
        return deletedTasks.stream().map(TaskDTO::getId).collect(Collectors.toSet());
    }

}
