package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ProjectWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.server.api.service.ProjectService;

import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "tm.common.api.webservice.ProjectWebService")
public class ProjectWebServiceBean implements ProjectWebService {

    private final ProjectService projectService;

    public ProjectWebServiceBean(ProjectService projectService) {
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
    public Collection<Project> getAllProjects() {
        return projectService.getAll();
    }

    @Override @NotNull
    public Collection<Project> getAllProjectsSorted(@Nullable final ComparatorType comparatorType) {
        return projectService.getAllSorted(comparatorType);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByName(@Nullable final String name) {
        return projectService.getAllByName(name);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByNameSorted(@Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return projectService.getAllByNameSorted(name, comparatorType);
    }

    @Override @Nullable
    public Project getProject(@Nullable String id) {
        return projectService.get(id);
    }

    @Override @NotNull
    public Collection<Project> searchProject(@Nullable final String searchLine) {
        return projectService.search(searchLine);
    }

    @Override @NotNull
    public Boolean saveProject(@Nullable final Project project) {
        return projectService.save(project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final Project project) {
        return projectService.delete(project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final String id) {
        return projectService.delete(id);
    }

    @Override @NotNull
    public Boolean deleteProjectsByIds(@Nullable final Collection<String> ids) {
        return projectService.deleteByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteProjectsByName(@Nullable final String name) {
        return projectService.deleteByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllProjects() {
        return projectService.deleteAll();
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable String projectId) {
        return projectService.deleteChildrenByParentId(projectId);
    }
}
