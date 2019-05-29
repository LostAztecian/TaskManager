package tm.server.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.comparator.ComparatorType;
import tm.server.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "taskService")
public interface TaskWebService {

    @Nullable @WebMethod(operationName = "getNewTask")
    Task getNewTask();

    @NotNull @WebMethod(operationName = "getAllTasks")
    Collection<Task> getAllTasks();

    @NotNull @WebMethod(operationName = "getAllTasksSorted")
    Collection<Task> getAllTasksSorted(@Nullable ComparatorType comparatorType);

    @NotNull @WebMethod(operationName = "getTasksByName")
    Collection<Task> getAllTasksByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "getTasksByNameSorted")
    Collection<Task> getAllTasksByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType);

    @Nullable @WebMethod(operationName = "getTaskById")
    Task getTask(@Nullable String id);

    @NotNull @WebMethod(operationName = "searchTask")
    Collection<Task> searchTask(@Nullable String searchLine);

    @NotNull @WebMethod(operationName = "saveTask")
    Boolean saveTask(@Nullable Task object);

    @NotNull @WebMethod(operationName = "deleteTask")
    Boolean deleteTask(@Nullable Task object);

    @NotNull @WebMethod(operationName = "deleteTaskById")
    Boolean deleteTask(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteTasksByIds")
    Boolean deleteTasksByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteTasksByName")
    Boolean deleteTasksByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllTasks")
    Boolean deleteAllTasks();

}
