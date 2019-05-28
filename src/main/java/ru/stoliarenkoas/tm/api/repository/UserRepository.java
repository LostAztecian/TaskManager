package ru.stoliarenkoas.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    @NotNull
    Optional<User> validate(@NotNull String login, @NotNull String pwdHash);

}
