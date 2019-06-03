package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.common.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "userService")
public interface UserWebService {

    @Nullable @WebMethod(operationName = "getNewUser")
    Collection<User> getNewUser();

    @NotNull @WebMethod(operationName = "getAllUsers")
    Collection<User> getAllUsers(@Nullable Session session);

    @NotNull @WebMethod(operationName = "getUsersByName")
    Collection<User> getUsersByName(@Nullable Session session, @Nullable String name);

    @Nullable @WebMethod(operationName = "getById")
    User getUser(@Nullable Session session, @Nullable String id);

    @NotNull @WebMethod(operationName = "updateUser")
    Boolean saveUser(@Nullable Session session, @Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUser")
    Boolean deleteUser(@Nullable Session session, @Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUserById")
    Boolean deleteUser(@Nullable Session session, @Nullable String id);

    @NotNull @WebMethod(operationName = "deleteUsersByIds")
    Boolean deleteUsersByIds(@Nullable Session session, @Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteUsersByName")
    Boolean deleteUsersByName(@Nullable Session session, @Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllUsers")
    Boolean deleteAllUsers(@Nullable Session session);

    @Nullable @WebMethod(operationName = "userLogin")
    Session login(@Nullable String login, @Nullable String password);

    @NotNull @WebMethod(operationName = "userLogout")
    Boolean logout(@Nullable Session session);

    @NotNull @WebMethod(operationName = "changeUserPassword")
    Boolean changePassword(@Nullable Session session, @Nullable String oldPassword, @Nullable String newPassword);

    @NotNull @WebMethod(operationName = "userShowProfile")
    String showProfile(@Nullable Session session);

    @NotNull @WebMethod(operationName = "userRegister")
    Boolean register(@Nullable String login, @Nullable String password);

}
