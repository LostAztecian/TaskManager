package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;
import tm.common.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "taskService")
public interface TaskWebService {

    @Nullable @WebMethod(operationName = "getNewTask")
    Task getNewTask() throws ServerException;

    @NotNull @WebMethod(operationName = "getAllTasks")
    Collection<Task> getAllTasks(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod(operationName = "getAllTasksSorted")
    Collection<Task> getAllTasksSorted(@Nullable Session session, @Nullable ComparatorType comparatorType) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByName")
    Collection<Task> getAllTasksByName(@Nullable Session session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByNameSorted")
    Collection<Task> getAllTasksByNameSorted(@Nullable Session session, @Nullable String name, @Nullable ComparatorType comparatorType) throws ServerException;

    @Nullable @WebMethod(operationName = "getTaskById")
    Task getTask(@Nullable Session session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "searchTask")
    Collection<Task> searchTask(@Nullable Session session, @Nullable String searchLine) throws ServerException;

    @NotNull @WebMethod(operationName = "saveTask")
    Boolean saveTask(@Nullable Session session, @Nullable Task object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTask")
    Boolean deleteTask(@Nullable Session session, @Nullable Task object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTaskById")
    Boolean deleteTask(@Nullable Session session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTasksByIds")
    Boolean deleteTasksByIds(@Nullable Session session, @Nullable Collection<String> ids) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteTasksByName")
    Boolean deleteTasksByName(@Nullable Session session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteAllTasks")
    Boolean deleteAllTasks(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod(operationName = "getTasksByProjectId")
    Collection<Task> getTasksByProjectId(@Nullable Session session, @Nullable String projectId) throws ServerException;

}
