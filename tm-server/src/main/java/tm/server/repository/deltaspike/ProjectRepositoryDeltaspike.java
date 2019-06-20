package tm.server.repository.deltaspike;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;
import tm.server.entity.Project;
import tm.server.entity.User;
import tm.server.repository.deltaspike.mapper.ProjectMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@MappingConfig(ProjectMapper.class)
@Repository(forEntity = Project.class)
public abstract class ProjectRepositoryDeltaspike {

    @Inject
    private EntityManager entityManager;

    @Query("SELECT e FROM Project e WHERE e.user.id = :userId")
    public abstract List<ProjectDTO> findByUserId(@QueryParam("userId") @NotNull String userId);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 ORDER BY ?2")
    public abstract List<ProjectDTO> findByUserIdEqualOrderBy(@NotNull String userId, @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 AND e.name = ?2")
    public abstract List<ProjectDTO> findByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 AND e.name = ?2 ORDER BY ?3")
    public abstract List<ProjectDTO> findByUserIdEqualAndNameEqualOrderBy(@NotNull String userId, @NotNull String name,  @NotNull String sortColumn);

    @Query(value = "SELECT e FROM Project e WHERE e.user.id = ?1 and e.id = ?2")
    public abstract ProjectDTO findAnyByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id);

    @Query("SELECT e FROM Project e WHERE (e.user.id = :userId) AND (e.name LIKE :line OR e.description LIKE :line)")
    public abstract List<ProjectDTO> search(@NotNull @QueryParam("userId") String userId, @NotNull @QueryParam("line") String searchLine);

    public void merge(@NotNull ProjectDTO projectDTO) {
        entityManager.merge(new Project(projectDTO, entityManager.find(User.class, projectDTO.getUserId())));
    }

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = ?1 AND e.id = ?2")
    public void deleteByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id) {
        entityManager.createQuery("DELETE FROM Project e WHERE e.user.id = ?1 AND e.id = ?2")
                .setParameter(1, userId)
                .setParameter(2, id)
                .executeUpdate();
    }

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = ?1 AND e.name = ?2")
    public int deleteByUserIdEqualAndNameEqual(@NotNull String userId, @NotNull String name) {
        return entityManager.createQuery("DELETE FROM Project e WHERE e.user.id = ?1 AND e.name = ?2")
                .setParameter(1, userId)
                .setParameter(2, name)
                .executeUpdate();
    }

//    @Query(value = "DELETE FROM Project e WHERE e.user.id = ?1")
    public int deleteByUserIdEqual(@NotNull String userId) {
        return entityManager.createQuery("DELETE FROM Project e WHERE e.user.id = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

}
