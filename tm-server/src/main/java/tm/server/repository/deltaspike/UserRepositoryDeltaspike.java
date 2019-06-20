package tm.server.repository.deltaspike;

import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.UserDTO;
import tm.server.entity.User;
import tm.server.repository.deltaspike.mapper.UserMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@MappingConfig(UserMapper.class)
@Repository(forEntity = User.class)
public abstract class UserRepositoryDeltaspike {

    @Inject
    private EntityManager entityManager;

    public abstract UserDTO findByLoginEqualAndPasswordHashEqual(@NotNull String login, @NotNull String passwordHash);

    public abstract UserDTO findAnyByIdEqual(@NotNull String id);

    public abstract List<UserDTO> findByLoginEqual(@NotNull String login);

    public abstract UserDTO findAnyByUserIdEqualAndIdEqual(@NotNull String userId, @NotNull String id);

    public Boolean persist(@QueryParam(value = "user") @NotNull UserDTO userDTO) {
        entityManager.persist(new User(userDTO));
        return true;
    }

    public Boolean save(@QueryParam(value = "user") @NotNull UserDTO userDTO) {
        return entityManager.merge(new User(userDTO)) != null;
    }

    public abstract UserDTO removeByIdEqual(@NotNull String id);

    public abstract Collection<UserDTO> removeByNameEqual(@NotNull String name);

    public abstract Collection<UserDTO> removeAll();

    public abstract Collection<UserDTO> findAll();

}
