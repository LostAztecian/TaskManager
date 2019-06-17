package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.exception.ServerException;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(name = "projectService")
public interface ProjectWebService {

    @NotNull @WebMethod(operationName = "getAllProjects")
    Collection<ProjectDTO> getAllProjects(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "getAllProjectsSorted")
    Collection<ProjectDTO> getAllProjectsSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws ServerException;

    @NotNull @WebMethod(operationName = "getProjectsByName")
    Collection<ProjectDTO> getProjectsByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "getProjectsByNameSorted")
    Collection<ProjectDTO> getProjectsByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws ServerException;

    @Nullable @WebMethod(operationName = "getProjectById")
    ProjectDTO getProject(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "searchProject")
    Collection<ProjectDTO> searchProject(@Nullable SessionDTO session, @Nullable String searchLine) throws ServerException;

    @NotNull @WebMethod(operationName = "saveProject")
    Boolean saveProject(@Nullable SessionDTO session, @Nullable ProjectDTO object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteProject")
    Boolean deleteProject(@Nullable SessionDTO session, @Nullable ProjectDTO object) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteProjectById")
    Boolean deleteProject(@Nullable SessionDTO session, @Nullable String id) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteProjectsByIds")
    Boolean deleteProjectsByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteProjectsByName")
    Boolean deleteProjectsByName(@Nullable SessionDTO session, @Nullable String name) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteAllProjects")
    Boolean deleteAllProjects(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod(operationName = "deleteProjectTasks")
    Boolean deleteProjectTasks(@Nullable SessionDTO session, @Nullable String projectId) throws ServerException;

}
