package tm.server.repository.deltaspike;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.TaskDTO;
import tm.server.entity.Project;
import tm.server.entity.Task;
import tm.server.repository.deltaspike.mapper.TaskMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@MappingConfig(TaskMapper.class)
@Repository(forEntity = Task.class)
public abstract class TaskRepositoryDeltaspike {

    @Inject
    private EntityManager entityManager;

    @Query("SELECT e FROM Task e WHERE e.project.user.id = :userId")
    public abstract List<TaskDTO> findByUserId(@QueryParam("userId") @NotNull String userId);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 ORDER BY ?2")
    public abstract List<TaskDTO> findByUserIdEqualOrderBy(@NotNull String userId, @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.name = ?2")
    public abstract List<TaskDTO> findByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name);
    
    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.project.id = ?2")
    public abstract List<TaskDTO> findByUserIdEqualAndProjectIdEqual(@NotNull String userId, @NotNull String projectId);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.name = ?2 ORDER BY ?3")
    public abstract List<TaskDTO> findByUserIdEqualAndNameEqualOrderBy(@NotNull String userId, @NotNull String name,  @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 and e.id = ?2")
    public abstract TaskDTO findAnyByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id);

    @Query("SELECT e FROM Task e WHERE (e.project.user.id = :userId) AND (e.name LIKE :line OR e.description LIKE :line)")
    public abstract List<TaskDTO> search(@NotNull @QueryParam("userId") String userId, @NotNull @QueryParam("line") String searchLine);

    public void merge(@NotNull TaskDTO taskDTO) {
        final Project project = entityManager.find(Project.class, taskDTO.getProjectId());
        entityManager.merge(new Task(taskDTO, project));
    }

    public void deleteByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id) {
        final Task task = entityManager.find(Task.class, id);
        if (id.equals(task.getProject().getUser().getId())) entityManager.remove(task);
    }

    public int deleteByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name) {
        final List<Task> tasks = entityManager
                .createQuery("SELECT e FROM Task e WHERE e.name = ?1", Task.class)
                .setParameter(1, name)
                .getResultList();
        tasks.removeIf(t -> !userId.equals(t.getProject().getUser().getId()));
        tasks.forEach(entityManager::remove);
        return tasks.size();
    }

    public int deleteByUserIdEqual(@NotNull String userId) {
        final List<Task> tasks = entityManager
                .createQuery("SELECT e FROM Task e", Task.class)
                .getResultList();
        tasks.removeIf(t -> !userId.equals(t.getProject().getUser().getId()));
        tasks.forEach(entityManager::remove);
        return tasks.size();
    }
    
}
