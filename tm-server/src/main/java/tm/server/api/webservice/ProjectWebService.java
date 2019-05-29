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
    Project getNewProject() throws JAXBException;

    @NotNull @WebMethod(operationName = "getAllProjects")
    Collection<Project> getAllProjects();

    @NotNull @WebMethod(operationName = "getAllProjectsSorted")
    Collection<Project> getAllProjectsSorted(@Nullable ComparatorType comparatorType);

    @NotNull @WebMethod(operationName = "getProjectsByName")
    Collection<Project> getProjectsByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "getProjectsByNameSorted")
    Collection<Project> getProjectsByNameSorted(@Nullable String name, @Nullable ComparatorType comparatorType);

    @Nullable @WebMethod(operationName = "getProjectById")
    Project getProject(@Nullable String id);

    @NotNull @WebMethod(operationName = "searchProject")
    Collection<Project> searchProject(@Nullable String searchLine);

    @NotNull @WebMethod(operationName = "saveProject")
    Boolean saveProject(@Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProject")
    Boolean deleteProject(@Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProjectById")
    Boolean deleteProject(@Nullable String id);

    @NotNull @WebMethod(operationName = "deleteProjectsByIds")
    Boolean deleteProjectsByIds(@Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteProjectsByName")
    Boolean deleteProjectsByName(@Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllProjects")
    Boolean deleteAllProjects();

}
