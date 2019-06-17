package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.common.entity.SessionDTO;
import tm.server.annotations.Jpa;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.jpa.ProjectRepositoryJPA;
import tm.server.api.service.ProjectService;
import tm.server.entity.Project;
import tm.server.entity.User;
import tm.server.repository.hibernate.ProjectRepositoryHibernate;
import tm.server.utils.SessionUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Jpa
@ApplicationScoped
@SuppressWarnings("Duplicates")
public class ProjectServiceJPA implements ProjectService {

    @Inject
    private EntityManagerFactory factory;

    @Inject
    private ServiceLocator serviceLocator;

    public ProjectServiceJPA() {
    }

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAll(@Nullable final SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        return repository.findAll(userId).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllSorted(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || comparatorType == null) return Collections.emptyList();
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        return repository.findAllAndSort(userId, comparatorType).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null) return Collections.emptyList();
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        return repository.findByName(userId, name).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<ProjectDTO> getAllByNameSorted(@Nullable final SessionDTO session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null ||name == null || comparatorType == null) return Collections.emptyList();
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        return repository.findByName(userId, name).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public ProjectDTO get(@Nullable final SessionDTO session, @Nullable final String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null) return null;
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        final Project project = repository.findOne(userId, id);
        if (project == null) return null;
        return project.toDTO();
    }

    @Override @NotNull
    public Collection<ProjectDTO> search(@Nullable final SessionDTO session, @Nullable final String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null || searchLine.isEmpty()) return Collections.emptyList();
        final ProjectRepositoryJPA repository = new ProjectRepositoryHibernate(factory.createEntityManager());
        return repository.search(userId, searchLine).stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Boolean save(@Nullable final SessionDTO session, @Nullable final ProjectDTO projectDTO) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectDTO == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final User user = entityManager.find(User.class, userId);
        System.out.println(user);
        if (user == null) return false;
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repositoryJPA.merge(userId, new Project(projectDTO, user));

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean delete(@Nullable final SessionDTO session, @Nullable final String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repositoryJPA.remove(userId, id) != null;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean delete(@Nullable final SessionDTO session, @Nullable final ProjectDTO projectDTO) throws Exception {
        if (projectDTO == null) return false;
        return delete(session, projectDTO.getId());
    }

    @Override @NotNull
    public Boolean deleteByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || ids.isEmpty()) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            for (final String id : ids) {
                repositoryJPA.remove(userId, id);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteProjectTasks(@Nullable final SessionDTO session, @Nullable final String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Project project = repositoryJPA.findOne(userId, projectId);
            if (project == null) return false;
            project.getTasks().clear();

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || name.isEmpty()) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repositoryJPA.removeByName(userId, name).size() > 0;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteAll(@Nullable final SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final ProjectRepositoryJPA repositoryJPA = new ProjectRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repositoryJPA.removeAll(userId).size() > 0;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

}
