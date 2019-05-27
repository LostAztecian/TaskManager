package ru.stoliarenkoas.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Optional;

public interface UserService extends Service<User> {

    @NotNull
    Optional<User> validate(final @Nullable String name, final @Nullable String pwdHash);

    void persist(final @Nullable User user);

}
