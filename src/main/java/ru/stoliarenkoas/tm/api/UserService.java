package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Optional;

public interface UserService extends Service<User> {

    @NotNull Optional<User> validate(final @Nullable String name, final @Nullable String pwdHash);

}
