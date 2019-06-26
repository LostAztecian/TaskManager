package tm.server;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.api.service.ProjectService;
import tm.server.api.service.TaskService;
import tm.server.api.service.UserService;
import tm.server.configuration.SpringJpaConfig;
import tm.server.service.ServerServiceImpl;
import tm.server.service.ProjectServiceImpl;
import tm.server.service.SessionServiceImpl;
import tm.server.service.TaskServiceImpl;
import tm.server.service.UserServiceImpl;

import static org.junit.Assert.*;

@ContextConfiguration(loader= AnnotationConfigContextLoader.class,
        classes={
                SpringJpaConfig.class,
                ProjectServiceImpl.class,
                TaskServiceImpl.class,
                UserServiceImpl.class,
                SessionServiceImpl.class,
                ServerServiceImpl.class
        })
@RunWith(SpringRunner.class)
public class ServiceTests {

    @Autowired @Qualifier("spring")
    private UserService userService;

    @Autowired @Qualifier("spring")
    private ProjectService projectService;

    @Autowired @Qualifier("spring")
    private TaskService taskService;

    private static ApplicationContext applicationContext;

    @BeforeClass
    public static void beforeSuite() {
        applicationContext = new AnnotationConfigApplicationContext("tm.server", "tm.server.repository");
    }

    public void afterSuite() {
    }

    @Test
    public void testUserService() throws Exception {
        final String login = "h2test";
        final String password = "h2test1";
        final String password2 = "h2test2";
        final Boolean registerResult = userService.register(login, password);
        assertNotNull(registerResult);

        final SessionDTO sessionDTO = userService.login(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());

        final Boolean changePasswordResult = userService.changePassword(sessionDTO, password, password2);
        assertTrue(changePasswordResult);

        final Boolean changePasswordResult2 = userService.changePassword(sessionDTO, password2, password);
        assertTrue(changePasswordResult2);

        final Boolean userLogoutResult = userService.logout(sessionDTO);
        assertTrue(userLogoutResult);
    }

    @Test
    public void testProjectService() throws Exception {
        final String login = "h2test";
        final String password = "h2test1";
        final Boolean registerResult = userService.register(login, password);
        assertNotNull(registerResult);

        final SessionDTO sessionDTO = userService.login(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());

        final String projectName = "prpr";
        final String projectDescription = "prpr desc";
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectName);
        projectDTO.setDescription(projectDescription);
        projectDTO.setUserId(sessionDTO.getUserId());
        projectDTO.setStartDate(projectDTO.getCreationDate());
        projectDTO.setEndDate(projectDTO.getCreationDate());
        final Boolean projectCreationResult = projectService.save(sessionDTO, projectDTO);
        assertTrue(projectCreationResult);

        final ProjectDTO receivedProject = projectService.get(sessionDTO, projectDTO.getId());
        assertNotNull(receivedProject);
        assertEquals(receivedProject.getName(), projectDTO.getName());

        final Boolean deletionResult = projectService.delete(sessionDTO, projectDTO.getId());
        assertTrue(deletionResult);
    }

    @Test
    public void testTaskService() throws Exception {
        final String login = "h2test";
        final String password = "h2test1";
        final Boolean registerResult = userService.register(login, password);
        assertNotNull(registerResult);

        final SessionDTO sessionDTO = userService.login(login, password);
        assertNotNull(sessionDTO);
        assertEquals(login, sessionDTO.getUserLogin());

        final String projectName = "pppr";
        final String projectDescription = "ppr desc";
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectName);
        projectDTO.setDescription(projectDescription);
        projectDTO.setUserId(sessionDTO.getUserId());
        projectDTO.setStartDate(projectDTO.getCreationDate());
        projectDTO.setEndDate(projectDTO.getCreationDate());
        final Boolean projectCreationResult = projectService.save(sessionDTO, projectDTO);
        assertTrue(projectCreationResult);

        final ProjectDTO receivedProject = projectService.get(sessionDTO, projectDTO.getId());
        assertNotNull(receivedProject);
        assertEquals(receivedProject.getName(), projectDTO.getName());


        final String taskName = "tasssk";
        final String taskDescription = "tasssk descript";
        final TaskDTO task = new TaskDTO();
        task.setProjectId(projectDTO.getId());
        task.setUserId(projectDTO.getUserId());
        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setStartDate(task.getCreationDate());
        task.setEndDate(task.getCreationDate());

        final Boolean taskCreationResult = taskService.save(sessionDTO, task);
        assertTrue(taskCreationResult);

        final TaskDTO receivedTask = taskService.get(sessionDTO, task.getId());
        assertNotNull(receivedTask);
        assertEquals(task.getName(), receivedTask.getName());

        final Boolean taskDeletionResult = taskService.delete(sessionDTO, task.getId());
        assertTrue(taskDeletionResult);

        final Boolean deletionResult = projectService.delete(sessionDTO, projectDTO.getId());
        assertTrue(deletionResult);
    }

}
