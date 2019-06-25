package tm.server.repository.deltaspike;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tm.server.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepositoryDeltaspike extends CrudRepository<Project, String> {

    @Query("SELECT e FROM Project e WHERE e.user.id = :userId")
    public abstract List<Project> findByUserId(@NotNull @Param("userId") String userId);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 ORDER BY ?2")
    public abstract List<Project> findByUserIdEqualOrderBy(@NotNull String userId, @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 AND e.name = ?2")
    public abstract List<Project> findByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 AND e.name = ?2 ORDER BY ?3")
    public abstract List<Project> findByUserIdEqualAndNameEqualOrderBy(@NotNull String userId, @NotNull String name,  @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 and e.id = ?2")
    public abstract Project findAnyByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id);

    @Query("SELECT e FROM Project e WHERE (e.user.id = :userId) AND (e.name LIKE :line OR e.description LIKE :line)")
    public abstract List<Project> search(@NotNull @Param("userId") String userId, @NotNull @Param("line") String searchLine);

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = ?1 AND e.id = ?2") @Modifying
    public void deleteByUser_IdAndId(@NotNull String userId, @NotNull String id);

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = :userId AND e.name = :name") @Modifying
    int deleteByUser_IdAndName(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name);

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = ?1") @Modifying
    public int deleteByUser_Id(@NotNull String userId);

}
