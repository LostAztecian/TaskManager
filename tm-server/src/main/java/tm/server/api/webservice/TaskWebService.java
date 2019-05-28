package tm.server.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.comparator.ComparatorType;
import tm.server.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.util.Collection;

@WebService(name = "taskService")
public interface TaskWebService {

    @WebMethod(operationName = "getNewTaskask")
    String getNew() throws JAXBException;

    @NotNull @WebMethod(operationName = "getAllTasks")
    Collection<Task> getAll();

    @NotNull @WebMethod(operationName = "getAllTasksSorted")
    Collection<Task> getAllSorted(@Nullable ComparatorType comparatorType);

    @NotNull @WebMethod(operationName = "getTasksByName")
    Collection<Task> getAllByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "getTasksByNameSorted")
    Collection<Task> getAllByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType);

    @Nullable @WebMethod(operationName = "getTaskById")
    Task get(@Nullable String id);

    @NotNull @WebMethod(operationName = "searchTask")
    Collection<Task> search(@Nullable String searchLine);

    @NotNull @WebMethod(operationName = "saveTask")
    Boolean save(@Nullable Task object);

    @NotNull @WebMethod(operationName = "deleteTask")
    Boolean delete(@Nullable Task object);

    @NotNull @WebMethod(operationName = "deleteTaskById")
    Boolean delete(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteTasksByIds")
    Collection<String> deleteByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteTasksByName")
    Collection<String> deleteByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllTasks")
    Boolean deleteAll();

}
