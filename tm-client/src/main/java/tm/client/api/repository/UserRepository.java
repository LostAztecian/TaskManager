package tm.client.api.repository;

import org.jetbrains.annotations.NotNull;
import tm.client.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    @NotNull
    Optional<User> validate(@NotNull String login, @NotNull String pwdHash);

}
