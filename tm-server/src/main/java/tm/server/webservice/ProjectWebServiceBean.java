package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import tm.common.api.webservice.ProjectWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.common.exception.ServerException;
import tm.server.api.service.ProjectService;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.ProjectWebService")
public class ProjectWebServiceBean implements ProjectWebService {

    @Autowired
    private ProjectService projectService;

    public ProjectWebServiceBean() {
    }

        public ProjectWebServiceBean(@NotNull final ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllProjects(@Nullable final SessionDTO session) throws ServerException {
        try {
            return projectService.getAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllProjectsSorted(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return projectService.getAllSorted(session, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<ProjectDTO> getProjectsByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return projectService.getAllByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<ProjectDTO> getProjectsByNameSorted(@Nullable final SessionDTO session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return projectService.getAllByNameSorted(session, name, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @Nullable
    public ProjectDTO getProject(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            return projectService.get(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Collection<ProjectDTO> searchProject(@Nullable final SessionDTO session, @Nullable final String searchLine) throws ServerException {
        try {
            return projectService.search(session, searchLine);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean saveProject(@Nullable final SessionDTO session, @Nullable final ProjectDTO project) throws ServerException {
        try {
            return projectService.save(session, project);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final SessionDTO session, @Nullable final ProjectDTO project) throws ServerException {
        try {
            return projectService.delete(session, project);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final SessionDTO session, @Nullable final String id) throws ServerException {
        try {
            System.out.println(session.getUserLogin() + " : " + id);
            return projectService.delete(session, id);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteProjectsByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws ServerException {
        try {
            return projectService.deleteByIds(session, ids);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteProjectsByName(@Nullable final SessionDTO session, @Nullable final String name) throws ServerException {
        try {
            return projectService.deleteByName(session, name);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteAllProjects(@Nullable final SessionDTO session) throws ServerException {
        try {
            return projectService.deleteAll(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable final SessionDTO session, @Nullable final String projectId) throws ServerException {
        try {
            return projectService.deleteProjectTasks(session, projectId);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
    
}
