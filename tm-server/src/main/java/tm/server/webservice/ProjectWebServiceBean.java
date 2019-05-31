package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ProjectWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.common.entity.Session;
import tm.server.api.service.ProjectService;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.ProjectWebService")
public class ProjectWebServiceBean implements ProjectWebService {

    private final ProjectService projectService;

    public ProjectWebServiceBean(@NotNull final ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Project getNewProject() {
        final Project project = new Project();
        project.setName("pr-01");
        project.setDescription("desc-01");
        project.setUserId("-1");

        return project;
    }

    @Override @NotNull
    public Collection<Project> getAllProjects(@Nullable final Session session) {
        return projectService.getAll(session);
    }

    @Override @NotNull
    public Collection<Project> getAllProjectsSorted(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        return projectService.getAllSorted(session, comparatorType);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByName(@Nullable final Session session, @Nullable final String name) {
        return projectService.getAllByName(session, name);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByNameSorted(@Nullable final Session session, @Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return projectService.getAllByNameSorted(session, name, comparatorType);
    }

    @Override @Nullable
    public Project getProject(@Nullable final Session session, @Nullable final String id) {
        return projectService.get(session, id);
    }

    @Override @NotNull
    public Collection<Project> searchProject(@Nullable final Session session, @Nullable final String searchLine) {
        return projectService.search(session, searchLine);
    }

    @Override @NotNull
    public Boolean saveProject(@Nullable final Session session, @Nullable final Project project) {
        return projectService.save(session, project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final Session session, @Nullable final Project project) {
        return projectService.delete(session, project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final Session session, @Nullable final String id) {
        return projectService.delete(session, id);
    }

    @Override @NotNull
    public Boolean deleteProjectsByIds(@Nullable final Session session, @Nullable final Collection<String> ids) {
        return projectService.deleteByIds(session, ids);
    }

    @Override @NotNull
    public Boolean deleteProjectsByName(@Nullable final Session session, @Nullable final String name) {
        return projectService.deleteByName(session, name);
    }

    @Override @NotNull
    public Boolean deleteAllProjects(@Nullable final Session session) {
        return projectService.deleteAll(session);
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable final Session session, @Nullable final String projectId) {
        return projectService.deleteProjectTasks(session, projectId);
    }
    
}
