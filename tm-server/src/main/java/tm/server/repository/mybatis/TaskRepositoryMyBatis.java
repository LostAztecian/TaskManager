package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Task;
import tm.server.api.repository.TaskRepository;
import tm.server.repository.mybatis.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskRepositoryMyBatis implements TaskRepository {

    private final SqlSession session;
    private final TaskMapper mapper;

    public TaskRepositoryMyBatis(SqlSessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
        session.getConfiguration().addMapper(TaskMapper.class);
        this.mapper = session.getMapper(TaskMapper.class);
    }

    @Override
    public @NotNull Collection<Task> findAll(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @NotNull Collection<Task> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        final String sortColumn;
        switch (comparatorType) {
            case BY_STATUS: {
                sortColumn = "status";
                break;
            }
            case BY_START_DATE: {
                sortColumn = "startDate";
                break;
            }
            case BY_END_DATE: {
                sortColumn = "endDate";
                break;
            }
            default: {
                sortColumn = "creationDate";
            }
        }
        return mapper.findByUserIdAndSort(userId, sortColumn);
    }

    @Override
    public @NotNull Collection<Task> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return mapper.findByName(userId, name);
    }

    @Override
    public @NotNull Collection<Task> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        final String sortColumn;
        switch (comparatorType) {
            case BY_STATUS: {
                sortColumn = "status";
                break;
            }
            case BY_START_DATE: {
                sortColumn = "startDate";
                break;
            }
            case BY_END_DATE: {
                sortColumn = "endDate";
                break;
            }
            default: {
                sortColumn = "creationDate";
            }
        }
        return mapper.findByNameAndSort(userId, name, sortColumn);
    }

    @Override
    public @NotNull Collection<Task> findByProjectId(@NotNull String userId, @NotNull String projectId) throws Exception {
        return mapper.findByProjectId(userId, projectId);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return mapper.findOne(userId, id);
    }

    @Override
    public @NotNull Collection<Task> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return mapper.search(userId, searchLine);
    }

    @Override
    public @NotNull Boolean persist(@NotNull Task task) throws Exception {
        mapper.persist(task);
        session.commit();
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull Task task) throws Exception {
        final Task oldTask = mapper.findOne(userId, task.getId());
        if (oldTask != null) mapper.removeById(userId, task.getId());
        mapper.persist(task);
        session.commit();
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final Task deletedTask = mapper.findOne(userId, id);
        if (deletedTask == null) return null;
        mapper.removeById(userId, deletedTask.getId());
        session.commit();
        return deletedTask.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull Task task) throws Exception {
        return remove(userId, task.getId());
    }

    @Override
    public @NotNull Collection<String> removeByProjectIds(@NotNull String userId, @NotNull Collection<String> ids) throws Exception {
        final Collection<Task> deletedTasks = new ArrayList<>();
        for (final String id : ids) {
            deletedTasks.addAll(mapper.findByProjectId(userId, id));
            mapper.removeByProjectId(userId, id);
        }
        session.commit();
        return deletedTasks.stream().map(Task::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final Collection<Task> deletedTasks = mapper.findByName(userId, name);
        if (deletedTasks.isEmpty()) return Collections.emptySet();
        mapper.removeByName(userId, name);
        session.commit();
        return deletedTasks.stream().map(Task::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final Collection<Task> deletedTasks = findAll(userId);
        if (deletedTasks.isEmpty()) return Collections.emptySet();
        mapper.clear(userId);
        session.commit();
        return deletedTasks.stream().map(Task::getId).collect(Collectors.toSet());
    }

}
