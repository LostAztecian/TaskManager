package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.common.entity.User;

public interface UserService extends Service<User> {

    @Nullable
    Session login(@Nullable String login, @Nullable String password);

    @NotNull
    Boolean logout (@Nullable Session session);

    @NotNull
    Boolean register(@Nullable String login, @Nullable String password);

    @NotNull
    String showUserProfile(@Nullable Session session);

    @NotNull
    Boolean changePassword(@Nullable Session session, @Nullable String oldPassword, @Nullable String newPassword);

}
