package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.UserWebService;
import tm.common.entity.User;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class UserServiceClient implements UserWebService {

    private final UserWebService userService;

    public UserServiceClient() {
        UserWebService userWebService = null;
        try {
            URL url = new URL("http://localhost:8080/userService?wsdl");
            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://webservice.server.tm/", "UserWebServiceBeanService");
            Service service = Service.create(url, qname);
            userWebService = service.getPort(UserWebService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        userService = userWebService;
    }

    @Override @Nullable
    public Collection<User> getNewUser() {
        return userService.getNewUser();
    }

    @Override @NotNull
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override @NotNull
    public Collection<User> getUsersByName(@Nullable final String name) {
        return userService.getUsersByName(name);
    }

    @Override @Nullable
    public User getUser(@Nullable final String id) {
        return userService.getUser(id);
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final User user) {
        return userService.saveUser(user);
    }

    @Override @NotNull
    public Boolean persistUser(@Nullable final User user) {
        return userService.persistUser(user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final User user) {
        return userService.deleteUser(user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final String id) {
        return userService.deleteUser(id);
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final Collection<String> ids) {
        return userService.deleteUsersByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final String name) {
        return userService.deleteUsersByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @Override @Nullable
    public User login(@Nullable String login, @Nullable final String password) {
        return userService.login(login, password);
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final String oldPassword, @Nullable final String newPassword) {
        return userService.changePassword(oldPassword, newPassword);
    }

    @Override @NotNull
    public Boolean logout() {
        return userService.logout();
    }

    @Override @NotNull
    public String showProfile() {
        return userService.showProfile();
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) {
        return userService.register(login, password);
    }
}
