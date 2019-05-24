package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    @NotNull Optional<User> validate(final @NotNull String login, final @NotNull String pwdHash);

}
