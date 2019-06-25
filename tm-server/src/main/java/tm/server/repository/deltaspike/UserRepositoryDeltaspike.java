package tm.server.repository.deltaspike;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tm.common.entity.UserDTO;
import tm.server.entity.User;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepositoryDeltaspike extends CrudRepository<User, String> {

    public abstract User findByLoginAndPasswordHash(@NotNull String login, @NotNull String passwordHash);

    public abstract User findAnyById(@NotNull String id);

    public abstract List<User> findByLogin(@NotNull String login);

    public abstract User removeById(@NotNull String id);

    public abstract Collection<User> removeByLogin(@NotNull String name);

}
