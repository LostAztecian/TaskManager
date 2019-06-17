package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.exception.ServerException;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "userService")
public interface UserWebService {

    @NotNull @WebMethod(operationName = "getAllUsers")
    Collection<UserDTO> getAllUsers(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "getUsersByName")
    Collection<UserDTO> getUsersByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @Nullable @WebMethod(operationName = "getById")
    UserDTO getUser(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "updateUser")
    Boolean saveUser(@Nullable SessionDTO session, @Nullable UserDTO user) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteUser")
    Boolean deleteUser(@Nullable SessionDTO session, @Nullable UserDTO user) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteUserById")
    Boolean deleteUser(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteUsersByIds")
    Boolean deleteUsersByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteUsersByName")
    Boolean deleteUsersByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteAllUsers")
    Boolean deleteAllUsers(@Nullable SessionDTO session) throws ServerException;

    @Nullable @WebMethod(operationName = "userLogin")
    SessionDTO login(@Nullable String login, @Nullable String password) throws ServerException;

    @NotNull @WebMethod(operationName = "userLogout")
    Boolean logout(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "changeUserPassword")
    Boolean changePassword(@Nullable SessionDTO session, @Nullable String oldPassword, @Nullable String newPassword) throws ServerException;

    @NotNull @WebMethod(operationName = "userShowProfile")
    String showProfile(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "userRegister")
    Boolean register(@Nullable String login, @Nullable String password) throws ServerException;

}
