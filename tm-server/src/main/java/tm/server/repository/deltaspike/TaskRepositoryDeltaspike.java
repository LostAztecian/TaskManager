package tm.server.repository.deltaspike;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tm.common.entity.TaskDTO;
import tm.server.entity.Project;
import tm.server.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface TaskRepositoryDeltaspike extends CrudRepository<Task, String> {

    @Query("SELECT e FROM Task e WHERE e.project.user.id = :userId")
    public abstract List<Task> findByUserId(@NotNull @Param("userId") String userId);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 ORDER BY ?2")
    public abstract List<Task> findByUserIdEqualOrderBy(@NotNull String userId, @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.name = ?2")
    public abstract List<Task> findByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.project.id = ?2")
    public abstract List<Task> findByUserIdEqualAndProjectIdEqual(@NotNull String userId, @NotNull String projectId);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 AND e.name = ?2 ORDER BY ?3")
    public abstract List<Task> findByUserIdEqualAndNameEqualOrderBy(@NotNull String userId, @NotNull String name,  @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Task e WHERE e.project.user.id = ?1 and e.id = ?2")
    public abstract Task findAnyByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id);

    @Query("SELECT e FROM Task e WHERE (e.project.user.id = :userId) AND (e.name LIKE :line OR e.description LIKE :line)")
    public abstract List<Task> search(@NotNull @Param("userId") String userId, @NotNull @Param("line") String searchLine);

    public void deleteByProject_User_IdAndId(@NotNull String userId, @NotNull String id);

    public int deleteByProject_User_IdAndName(@NotNull String userId, @NotNull String name);

    public int deleteByProject_User_Id(@NotNull String userId);
    
}
