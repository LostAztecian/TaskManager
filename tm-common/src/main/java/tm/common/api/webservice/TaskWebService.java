package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.exception.ServerException;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "taskService")
public interface TaskWebService {

    @NotNull @WebMethod(operationName = "getAllTasks")
    Collection<TaskDTO> getAllTasks(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "getAllTasksSorted")
    Collection<TaskDTO> getAllTasksSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByName")
    Collection<TaskDTO> getAllTasksByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByNameSorted")
    Collection<TaskDTO> getAllTasksByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws ServerException;

    @Nullable @WebMethod(operationName = "getTaskById")
    TaskDTO getTask(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "searchTask")
    Collection<TaskDTO> searchTask(@Nullable SessionDTO session, @Nullable String searchLine) throws ServerException;

    @NotNull @WebMethod(operationName = "saveTask")
    Boolean saveTask(@Nullable SessionDTO session, @Nullable TaskDTO object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTask")
    Boolean deleteTask(@Nullable SessionDTO session, @Nullable TaskDTO object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTaskById")
    Boolean deleteTask(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTasksByIds")
    Boolean deleteTasksByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTasksByName")
    Boolean deleteTasksByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteAllTasks")
    Boolean deleteAllTasks(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByProjectId")
    Collection<TaskDTO> getTasksByProjectId(@Nullable SessionDTO session, @Nullable String projectId) throws ServerException;

}
