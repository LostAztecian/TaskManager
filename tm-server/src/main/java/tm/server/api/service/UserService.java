package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.User;

import java.util.Optional;

public interface UserService extends Service<User> {

    @NotNull
    Boolean persist(@Nullable User user);

    @Nullable
    User login(@Nullable String login, @Nullable String password);

    @NotNull
    Boolean register(@Nullable String login, @Nullable String password);

    @NotNull
    Boolean logout();

    @NotNull
    String showUserProfile();

    @NotNull
    Boolean changePassword(@Nullable String oldPassword, @Nullable String newPassword);

}
