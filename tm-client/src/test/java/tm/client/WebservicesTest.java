package java.tm.client;

import org.junit.Test;
import tm.common.api.webservice.*;
import tm.server.webservice.ProjectWebServiceBeanService;
import tm.server.webservice.ServerWebServiceBeanService;
import tm.server.webservice.TaskWebServiceBeanService;
import tm.server.webservice.UserWebServiceBeanService;

import static org.junit.Assert.*;


public class WebservicesTest {

    private UserService userService = new UserWebServiceBeanService().getUserWebServiceBeanPort();
    private ServerService serverService  = new ServerWebServiceBeanService().getServerWebServiceBeanPort();
    private ProjectService projectService = new ProjectWebServiceBeanService().getProjectWebServiceBeanPort();
    private TaskService taskService = new TaskWebServiceBeanService().getTaskWebServiceBeanPort();

    private final String login = "test";
    private final String password = "test";

    @Test
    public void testRegister() {
        final Boolean registerResult = userService.userRegister(login, password);
        assertNotNull(registerResult);
    }

    @Test
    public void testLogin() {
        final SessionDTO sessionDTO = userService.userLogin(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());
        final Boolean logoutResult = userService.userLogout(sessionDTO);
        assertNotNull(logoutResult);
    }

    @Test
    public void testShowProfile() {
        final SessionDTO sessionDTO = userService.userLogin(login, password);
        assertEquals(login, sessionDTO.getUserLogin());
        final String profileInfo = userService.userShowProfile(sessionDTO);
        assertNotNull(profileInfo);
        assertFalse(profileInfo.isEmpty());
    }

    @Test
    public void testProject() {
        final String projectId = "test-project-id";
        final String projectName = "test-name";
        final String projectDescription = "test-desc";
        final ProjectDTO createdProject = new ProjectDTO();
        createdProject.setId(projectId);
        createdProject.setName(projectName);
        createdProject.setDescription(projectDescription);

        final SessionDTO sessionDTO = userService.userLogin(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());

        final Boolean creationResult = projectService.saveProject(sessionDTO, createdProject);
        assertNotNull(creationResult);

        final ProjectDTO receivedProject = projectService.getProjectById(sessionDTO, projectId);
        assertNotNull(receivedProject);
        assertEquals(projectName, receivedProject.getName());
        assertEquals(projectDescription, receivedProject.getDescription());

        final Boolean deletionResult = projectService.deleteProjectsByName(sessionDTO, projectName);
        assertNotNull(deletionResult);
    }


    @Test
    public void testTask() {
        final String projectId = "test-project-id", taskId = "test-task-id";
        final String projectName = "test-name", taskName = "task-name";
        final String projectDescription = "test-desc", taskDescription = "task-desc";

        final ProjectDTO createdProject = new ProjectDTO();
        createdProject.setId(projectId);
        createdProject.setName(projectName);
        createdProject.setDescription(projectDescription);

        final TaskDTO createdTask = new TaskDTO();
        createdTask.setId(taskId);
        createdTask.setProjectId(projectId);
        createdTask.setName(taskName);
        createdTask.setDescription(taskDescription);

        final SessionDTO sessionDTO = userService.userLogin(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());

        final Boolean creationResult = projectService.saveProject(sessionDTO, createdProject);
        assertNotNull(creationResult);

        final Boolean taskCreationResult = taskService.saveTask(sessionDTO, createdTask);
        assertNotNull(taskCreationResult);

        final TaskDTO receivedTask = taskService.getTaskById(sessionDTO, taskId);
        assertNotNull(receivedTask);
        assertEquals(taskName, receivedTask.getName());

        final Boolean taskDeletionResult = taskService.deleteTask(sessionDTO, createdTask);

        final Boolean deletionResult = projectService.deleteProjectsByName(sessionDTO, projectName);
        assertNotNull(deletionResult);
    }

}
