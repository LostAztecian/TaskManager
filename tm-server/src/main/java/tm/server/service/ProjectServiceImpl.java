package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.server.api.ServiceLocator;
import tm.server.api.service.ProjectService;
import tm.server.entity.Project;
import tm.server.entity.User;
import tm.server.repository.ProjectRepositorySpring;
import tm.server.util.DatabaseUtil;
import tm.server.util.SessionUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("spring")
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepositorySpring projectRepositorySpring;

    private ServiceLocator serviceLocator;

    @Autowired
    public void setProjectRepositorySpring(ProjectRepositorySpring projectRepositorySpring) {
        this.projectRepositorySpring = projectRepositorySpring;
    }

    @Autowired
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAll(@Nullable SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        return projectRepositorySpring.findByUser_Id(userId).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllSorted(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        return projectRepositorySpring.findByUser_IdOrderBy(userId, DatabaseUtil.getSortColumn(comparatorType)).stream()
                .map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return projectRepositorySpring.findByUser_IdAndName(userId, name).stream().map(Project::toDTO)
                .collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllByNameSorted(@Nullable SessionDTO session, @Nullable String name, @Nullable ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return Collections.emptyList();
        return projectRepositorySpring.findByUser_IdAndNameOrderBy(userId, name, DatabaseUtil.getSortColumn(comparatorType))
                .stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public ProjectDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return null;
        return projectRepositorySpring.findAnyByUser_IdAndId(userId, id).toDTO();
    }

    @Override @NotNull
    public Collection<ProjectDTO> search(@Nullable SessionDTO session, @Nullable String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || userId.isEmpty() || searchLine.isEmpty()) return Collections.emptyList();
        searchLine = "%" + searchLine + "%";
        return projectRepositorySpring.search(userId, searchLine).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Boolean save(@Nullable SessionDTO session, @Nullable ProjectDTO projectDTO) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectDTO == null || userId.isEmpty()) return false;
        if (!userId.equals(projectDTO.getUserId())) return false;
        projectRepositorySpring.save(new Project(projectDTO, new User(serviceLocator.getUserService().get(session, userId))));
        return true;
    }

    @Override @NotNull
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null || userId.isEmpty() || id.isEmpty()) return false;
        projectRepositorySpring.deleteByUser_IdAndId(userId, id);
        return true;
    }

    @Override @NotNull
    public Boolean delete(@Nullable SessionDTO session, @Nullable ProjectDTO projectDTO) throws Exception {
        if (projectDTO == null) return false;
        return delete(session, projectDTO.getId());
    }

    @Override @NotNull
    public Boolean deleteByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || userId.isEmpty() || ids.isEmpty()) return false;
        for (final String id : ids) {
            projectRepositorySpring.deleteByUser_IdAndId(userId, id);
        }
        return true;
    }

    @Override @NotNull
    public Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || userId.isEmpty() || name.isEmpty()) return false;
        return projectRepositorySpring.deleteByUser_IdAndName(userId, name) > 0;
    }

    @Override @NotNull
    public Boolean deleteAll(@Nullable SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || userId.isEmpty()) return false;
        return projectRepositorySpring.deleteByUser_Id(userId) > 0;
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable SessionDTO session, @Nullable String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null || userId.isEmpty() || projectId.isEmpty()) return false;
        return !serviceLocator.getTaskService().removeTasksByProjectIds(session, Collections.singletonList(projectId)).isEmpty();
    }
    
}
