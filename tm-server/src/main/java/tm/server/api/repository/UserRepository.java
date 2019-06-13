package tm.server.api.repository;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.UserDTO;

import java.util.Optional;

public interface UserRepository extends Repository<UserDTO> {

    @NotNull
    Optional<UserDTO> validate(@NotNull String login, @NotNull String pwdHash) throws Exception;

}
