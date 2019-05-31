package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.common.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "projectService")
public interface ProjectWebService {

    @WebMethod(operationName = "getNewProject")
    Project getNewProject();

    @NotNull @WebMethod(operationName = "getAllProjects")
    Collection<Project> getAllProjects(@Nullable Session session);

    @NotNull @WebMethod(operationName = "getAllProjectsSorted")
    Collection<Project> getAllProjectsSorted(@Nullable Session session, @Nullable ComparatorType comparatorType);

    @NotNull @WebMethod(operationName = "getProjectsByName")
    Collection<Project> getProjectsByName(@Nullable Session session, @Nullable String name);

    @NotNull @WebMethod(operationName = "getProjectsByNameSorted")
    Collection<Project> getProjectsByNameSorted(@Nullable Session session, @Nullable String name, @Nullable ComparatorType comparatorType);

    @Nullable @WebMethod(operationName = "getProjectById")
    Project getProject(@Nullable Session session, @Nullable String id);

    @NotNull @WebMethod(operationName = "searchProject")
    Collection<Project> searchProject(@Nullable Session session, @Nullable String searchLine);

    @NotNull @WebMethod(operationName = "saveProject")
    Boolean saveProject(@Nullable Session session, @Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProject")
    Boolean deleteProject(@Nullable Session session, @Nullable Project object);

    @NotNull @WebMethod(operationName = "deleteProjectById")
    Boolean deleteProject(@Nullable Session session, @Nullable String id);

    @NotNull @WebMethod(operationName = "deleteProjectsByIds")
    Boolean deleteProjectsByIds(@Nullable Session session, @Nullable Collection<String> ids);

    @NotNull @WebMethod(operationName = "deleteProjectsByName")
    Boolean deleteProjectsByName(@Nullable Session session, @Nullable String name);

    @NotNull @WebMethod(operationName = "deleteAllProjects")
    Boolean deleteAllProjects(@Nullable Session session);

    @NotNull @WebMethod(operationName = "deleteProjectTasks")
    Boolean deleteProjectTasks(@Nullable Session session, @Nullable String projectId);

}
