package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
import tm.common.entity.Session;
import tm.server.api.service.UserService;
import tm.common.api.webservice.UserWebService;
import tm.common.entity.User;
import tm.server.utils.CypherUtil;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.UserWebService")
public class UserWebServiceBean implements UserWebService {

    private final UserService userService;

    public UserWebServiceBean(@NotNull final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Collection<User> getNewUser() {
        final User userOne = new User();
        userOne.setLogin("gael");
        userOne.setPasswordHash(CypherUtil.getMd5("knight"));
        userOne.setRole(User.Role.ADMIN);

        final User userTwo = new User();
        userTwo.setLogin("thy");
        userTwo.setPasswordHash(CypherUtil.getMd5("thine"));
        userTwo.setRole(User.Role.ADMIN);

        final Collection<User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);

        return list;
    }

    @Override @NotNull
    public Collection<User> getAllUsers(@Nullable final Session session) throws ServerException {
        try {
            return userService.getAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<User> getUsersByName(@Nullable final Session session, @Nullable final String name) throws ServerException {
        try {
            return userService.getAllByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public User getUser(@Nullable final Session session, @Nullable final String id) throws ServerException {
        try {
            return userService.get(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final Session session, @Nullable final User user) throws ServerException {
        try {
            return userService.save(session, user);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session, @Nullable final User user) throws ServerException {
        try {
            return userService.delete(session, user);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session, @Nullable final String id) throws ServerException {
        try {
            return userService.delete(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final Session session, @Nullable final Collection<String> ids) throws ServerException {
        try {
            return userService.deleteByIds(session, ids);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final Session session, @Nullable final String name) throws ServerException {
        try {
            return userService.deleteByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteAllUsers(@Nullable final Session session) throws ServerException {
        try {
            return userService.deleteAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public Session login(@Nullable final String login, @Nullable final String password) throws ServerException {
        try {
            return userService.login(login, password);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean logout(@Nullable final Session session) throws ServerException {
        try {
            return userService.logout(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final Session session, @Nullable final String oldPassword, @Nullable final String newPassword) throws ServerException {
        try {
            return userService.changePassword(session, oldPassword, newPassword);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public String showProfile(@Nullable final Session session) throws ServerException {
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
