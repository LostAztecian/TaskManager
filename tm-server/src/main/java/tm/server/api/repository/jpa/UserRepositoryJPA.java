package tm.server.api.repository.jpa;

import org.jetbrains.annotations.NotNull;
import tm.server.api.repository.Repository;
import tm.server.entity.User;

import java.util.Optional;

public interface UserRepositoryJPA extends Repository<User> {

    @NotNull
    Optional<User> validate(@NotNull String login, @NotNull String pwdHash) throws Exception;

}
