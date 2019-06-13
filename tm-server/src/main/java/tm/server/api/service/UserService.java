package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;

public interface UserService extends Service<UserDTO> {

    @Nullable
    SessionDTO login(@Nullable String login, @Nullable String password) throws Exception;

    @NotNull
    Boolean logout (@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean register(@Nullable String login, @Nullable String password) throws Exception;

    @NotNull
    String showUserProfile(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean changePassword(@Nullable SessionDTO session, @Nullable String oldPassword, @Nullable String newPassword) throws Exception;

}
