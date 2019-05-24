package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.UserRepository;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Optional;

public class UserMapRepository extends AbstractMapRepository<User> implements UserRepository {

    public @NotNull Optional<User> validate(final @NotNull String login, final @NotNull String pwdHash) {
        final Optional<User> user = map.values().stream()
                .filter(u -> login.equals(u.getLogin()) && pwdHash.equals(u.getPwdHash()))
                .findAny();
        return user;
    }

}
