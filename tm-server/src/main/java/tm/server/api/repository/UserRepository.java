package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import tm.server.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    @NotNull
    Optional<User> validate(@NotNull String login, @NotNull String pwdHash);

}
