package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import tm.common.api.webservice.UserWebService;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;
import tm.common.exception.ServerException;
import tm.server.api.service.UserService;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.UserWebService")
public class UserWebServiceBean implements UserWebService {

    @Autowired
    private UserService userService;

    public UserWebServiceBean(@NotNull final UserService userService) {
        this.userService = userService;
    }

    public UserWebServiceBean() {
    }

    @Override @NotNull
    public Collection<UserDTO> getAllUsers(@Nullable final SessionDTO session) throws ServerException {
        try {
            return userService.getAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<UserDTO> getUsersByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return userService.getAllByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public UserDTO getUser(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            return userService.get(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final SessionDTO session, @Nullable final UserDTO user) throws ServerException {
        try {
            return userService.save(session, user);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final SessionDTO session, @Nullable final UserDTO user) throws ServerException {
        try {
            return userService.delete(session, user);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            return userService.delete(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws ServerException {
        try {
            return userService.deleteByIds(session, ids);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return userService.deleteByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteAllUsers(@Nullable final SessionDTO session) throws ServerException {
        try {
            return userService.deleteAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public SessionDTO login(@Nullable final String login, @Nullable final String password) throws ServerException {
        try {
            return userService.login(login, password);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean logout(@Nullable final SessionDTO session) throws ServerException {
        try {
            return userService.logout(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final SessionDTO session, @Nullable final String oldPassword, @Nullable final String newPassword) throws ServerException {
        try {
            return userService.changePassword(session, oldPassword, newPassword);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public String showProfile(@Nullable final SessionDTO session) throws ServerException {
        try {
            return userService.showUserProfile(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) throws ServerException {
        try {
            return userService.register(login, password);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
}
