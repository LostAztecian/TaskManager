package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    public UserWebServiceBean(UserService userService) {
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
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @Override @NotNull
    public Collection<User> getUsersByName(@Nullable final String name) {
        return userService.getAllByName(name);
    }

    @Override @Nullable
    public User getUser(@Nullable final String id) {
        return userService.get(id);
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final User user) {
        return userService.save(user);
    }

    @Override @NotNull
    public Boolean persistUser(@Nullable final User user) {
        return userService.persist(user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final User user) {
        return userService.delete(user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final String id) {
        return userService.delete(id);
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final Collection<String> ids) {
        return userService.deleteByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final String name) {
        return userService.deleteByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllUsers() {
        return userService.deleteAll();
    }

    @Override @Nullable
    public User login(@Nullable String login, @Nullable String password) {
        return userService.login(login, password);
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable String oldPassword, @Nullable String newPassword) {
        return userService.changePassword(oldPassword, newPassword);
    }

    @Override @NotNull
    public Boolean logout() {
        return userService.logout();
    }

    @Override @NotNull
    public String showProfile() {
        return userService.showUserProfile();
    }

    @Override @NotNull
    public Boolean register(@Nullable String login, @Nullable String password) {
        return userService.register(login, password);
    }
}
