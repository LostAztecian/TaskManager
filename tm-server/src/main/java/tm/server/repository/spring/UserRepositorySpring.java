package tm.server.repository.spring;

import org.springframework.data.repository.CrudRepository;
import tm.server.entity.User;

public interface UserRepositorySpring extends CrudRepository<User, String> {
}
