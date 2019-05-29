package tm.server.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "userService")
public interface UserWebService {

    @Nullable @WebMethod(operationName = "getNewUser")
    Collection<User> getNewUser();

    @NotNull @WebMethod(operationName = "getAllUsers")
    Collection<User> getAllUsers();

    @NotNull @WebMethod(operationName = "getUsersByName")
    Collection<User> getUsersByName(@Nullable String name);

    @Nullable @WebMethod(operationName = "getById")
    User getUser(@Nullable String id);

    @NotNull @WebMethod(operationName = "updateUser")
    Boolean saveUser(@Nullable User user);

    @NotNull @WebMethod(operationName = "saveUser")
    Boolean persistUser(@Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUser")
    Boolean deleteUser(@Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUserById")
    Boolean deleteUser(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteUsersByIds")
    Boolean deleteUsersByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteUsersByName")
    Boolean deleteUsersByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllUsers")
    Boolean deleteAllUsers();

}
