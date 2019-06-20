package tm.server.repository.deltaspike;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;
import tm.server.entity.Session;
import tm.server.entity.User;
import tm.server.repository.deltaspike.mapper.SessionMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@MappingConfig(SessionMapper.class)
@Repository(forEntity = Session.class)
public abstract class SessionRepositoryDeltaspike {

    @Inject
    private EntityManager entityManager;

    public abstract List<SessionDTO> findAll();

    public abstract SessionDTO findByIdEqual(@NotNull String id);

    public abstract List<SessionDTO> findByUserIdEqual(@NotNull String userId);

    public void persist(SessionDTO entity) {
        entityManager.persist(new Session(entity, entityManager.find(User.class, entity.getUserId())));
    }

    public void deleteByIdEqual(@NotNull String id) {
        entityManager.createQuery("DELETE FROM Session e WHERE e.id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    public abstract int deleteByUserIdEqual(@NotNull String userId);

    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Session")
                .executeUpdate();
    }

}
