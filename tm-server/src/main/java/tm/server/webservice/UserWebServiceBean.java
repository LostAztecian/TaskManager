package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    public Collection<User> getAllUsers(@Nullable final Session session) {
        return userService.getAll(session);
    }

    @Override @NotNull
    public Collection<User> getUsersByName(@Nullable final Session session, @Nullable final String name) {
        return userService.getAllByName(session, name);
    }

    @Override @Nullable
    public User getUser(@Nullable final Session session, @Nullable final String id) {
        return userService.get(session, id);
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final Session session, @Nullable final User user) {
        return userService.save(session, user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session, @Nullable final User user) {
        return userService.delete(session, user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session, @Nullable final String id) {
        return userService.delete(session, id);
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final Session session, @Nullable final Collection<String> ids) {
        return userService.deleteByIds(session, ids);
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final Session session, @Nullable final String name) {
        return userService.deleteByName(session, name);
    }

    @Override @NotNull
    public Boolean deleteAllUsers(@Nullable final Session session) {
        return userService.deleteAll(session);
    }

    @Override @Nullable
    public Session login(@Nullable final String login, @Nullable final String password) {
        return userService.login(login, password);
    }

    @Override @NotNull
    public Boolean logout(@Nullable final Session session) {
        return userService.logout(session);
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final Session session, @Nullable final String oldPassword, @Nullable final String newPassword) {
        return userService.changePassword(session, oldPassword, newPassword);
    }

    @Override @NotNull
    public String showProfile(@Nullable final Session session) {
        return userService.showUserProfile(session);
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) {
        return userService.register(login, password);
    }
}
