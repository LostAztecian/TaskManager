package tm.server.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.comparator.ComparatorType;
import tm.server.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.util.Collection;

@WebService(name = "projectService")
public interface ProjectWebService {

    @WebMethod(operationName = "getNewProject")
    String getNew() throws JAXBException;

    @NotNull @WebMethod(operationName = "getAllProjects")
    Collection<Project> getAll();

    @NotNull @WebMethod(operationName = "getAllProjectsSorted")
    Collection<Project> getAllSorted(@Nullable ComparatorType comparatorType);

    @NotNull @WebMethod(operationName = "getProjectsByName")
    Collection<Project> getAllByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "getProjectsByNameSorted")
    Collection<Project> getAllByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType);

    @Nullable @WebMethod(operationName = "getProjectById")
    Project get(@Nullable String id);

    @NotNull @WebMethod(operationName = "searchProject")
    Collection<Project> search(@Nullable String searchLine);

    @NotNull @WebMethod(operationName = "saveProject")
    Boolean save(@Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProject")
    Boolean delete(@Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProjectById")
    Boolean delete(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteProjectsByIds")
    Collection<String> deleteByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteProjectsByName")
    Collection<String> deleteByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllProjects")
    Boolean deleteAll();

}
