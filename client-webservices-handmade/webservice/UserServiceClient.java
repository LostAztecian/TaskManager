package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.UserWebService;
import tm.common.entity.Session;
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
    public Collection<User> getAllUsers(@Nullable final Session session) {
        return userService.getAllUsers(session);
    }

    @Override @NotNull
    public Collection<User> getUsersByName(@Nullable final Session session,  @Nullable final String name) {
        return userService.getUsersByName(session, name);
    }

    @Override @Nullable
    public User getUser(@Nullable final Session session,  @Nullable final String id) {
        return userService.getUser(session, id);
    }

    @Override @NotNull
    public Boolean saveUser(@Nullable final Session session,  @Nullable final User user) {
        return userService.saveUser(session, user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session,  @Nullable final User user) {
        return userService.deleteUser(session, user);
    }

    @Override @NotNull
    public Boolean deleteUser(@Nullable final Session session,  @Nullable final String id) {
        return userService.deleteUser(session, id);
    }

    @Override @NotNull
    public Boolean deleteUsersByIds(@Nullable final Session session,  @Nullable final Collection<String> ids) {
        return userService.deleteUsersByIds(session, ids);
    }

    @Override @NotNull
    public Boolean deleteUsersByName(@Nullable final Session session,  @Nullable final String name) {
        return userService.deleteUsersByName(session, name);
    }

    @Override @NotNull
    public Boolean deleteAllUsers(@Nullable final Session session) {
        return userService.deleteAllUsers(session);
    }

    @Override @Nullable
    public Session login(@Nullable final String login, @Nullable final String password) {
        return userService.login(login, password);
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final Session session,  @Nullable final String oldPassword, @Nullable final String newPassword) {
        return userService.changePassword(session, oldPassword, newPassword);
    }

    @Override @NotNull
    public String showProfile(@Nullable final Session session) {
        return userService.showProfile(session);
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) {
        return userService.register(login, password);
    }
}
