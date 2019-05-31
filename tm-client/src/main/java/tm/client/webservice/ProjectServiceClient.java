package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ProjectWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class ProjectServiceClient implements ProjectWebService {

    private final ProjectWebService projectService;

    public ProjectServiceClient() {
        ProjectWebService projectWebService = null;
        try {
            URL url = new URL("http://localhost:8080/projectService?wsdl");
            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://webservice.server.tm/", "ProjectWebServiceBeanService");
            Service service = Service.create(url, qname);
            projectWebService = service.getPort(ProjectWebService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        projectService = projectWebService;
    }

    @Override @Nullable
    public Project getNewProject() {
        return projectService.getNewProject();
    }

    @Override @NotNull
    public Collection<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @Override @NotNull
    public Collection<Project> getAllProjectsSorted(@Nullable final ComparatorType comparatorType) {
        return projectService.getAllProjectsSorted(comparatorType);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByName(@Nullable final String name) {
        return projectService.getProjectsByName(name);
    }

    @Override @NotNull
    public Collection<Project> getProjectsByNameSorted(@Nullable final String name, @Nullable final ComparatorType comparatorType) {
        return projectService.getProjectsByNameSorted(name, comparatorType);
    }

    @Override @Nullable
    public Project getProject(@Nullable final String id) {
        return projectService.getProject(id);
    }

    @Override @NotNull
    public Collection<Project> searchProject(@Nullable final String searchLine) {
        return projectService.searchProject(searchLine);
    }

    @Override @NotNull
    public Boolean saveProject(@Nullable final Project project) {
        return projectService.saveProject(project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final Project project) {
        return projectService.deleteProject(project);
    }

    @Override @NotNull
    public Boolean deleteProject(@Nullable final String id) {
        return projectService.deleteProject(id);
    }

    @Override @NotNull
    public Boolean deleteProjectsByIds(@Nullable final Collection<String> ids) {
        return projectService.deleteProjectsByIds(ids);
    }

    @Override @NotNull
    public Boolean deleteProjectsByName(@Nullable final String name) {
        return projectService.deleteProjectsByName(name);
    }

    @Override @NotNull
    public Boolean deleteAllProjects() {
        return projectService.deleteAllProjects();
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable final String projectId) {
        return projectService.deleteProjectTasks(projectId);
    }
}
