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
    Collection<User> getNew();

    @NotNull @WebMethod(operationName = "getAllUsers")
    Collection<User> getAll();

    @NotNull @WebMethod(operationName = "getUsersByName")
    Collection<User> getAllByName(@Nullable String name);

    @Nullable @WebMethod(operationName = "getById")
    User get(@Nullable String id);

    @NotNull @WebMethod(operationName = "updateUser")
    Boolean save(@Nullable User user);

    @NotNull @WebMethod(operationName = "saveUser")
    Boolean persist(@Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUser")
    Boolean delete(@Nullable User user);

    @NotNull @WebMethod(operationName = "deleteUserById")
    Boolean delete(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteUsersByIds")
    Collection<String> deleteByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteUsersByName")
    Collection<String> deleteByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllUsers")
    Boolean deleteAll();

}
