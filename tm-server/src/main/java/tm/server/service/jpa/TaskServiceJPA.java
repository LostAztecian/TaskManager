package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.common.entity.TaskDTO;
import tm.server.annotations.Jpa;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.jpa.TaskRepositoryJPA;
import tm.server.api.service.TaskService;
import tm.server.entity.Project;
import tm.server.entity.Task;
import tm.server.repository.hibernate.TaskRepositoryHibernate;
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
public class TaskServiceJPA implements TaskService {

    @Inject
    private EntityManagerFactory factory;

    @Inject
    private ServiceLocator serviceLocator;

    public TaskServiceJPA() {
    }

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public Collection<TaskDTO> getAll(@Nullable final SessionDTO session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.findAll(userId).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllSorted(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || comparatorType == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.findAllAndSort(userId, comparatorType).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.findByName(userId, name).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getAllByNameSorted(@Nullable final SessionDTO session, @Nullable final String name, @Nullable final ComparatorType comparatorType) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null || comparatorType == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.findByNameAndSort(userId, comparatorType, name).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<TaskDTO> getTasksByProjectId(@Nullable final SessionDTO session, @Nullable final String projectId) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || projectId == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.findByProjectId(userId, projectId).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public TaskDTO get(@Nullable final SessionDTO session, @Nullable final String id) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || id == null) return null;

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final Task task = repository.findOne(userId, id);
        if (task == null) return null;
        return task.toDTO();
    }

    @Override @NotNull
    public Collection<TaskDTO> search(@Nullable final SessionDTO session, @Nullable final String searchLine) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || searchLine == null) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        return repository.search(userId, searchLine).stream().map(Task::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Boolean save(@Nullable final SessionDTO session, @Nullable final TaskDTO taskDTO) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || taskDTO == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Project project = entityManager.find(Project.class, taskDTO.getProjectId());
            if (project == null) return false;
            repository.merge(userId, new Task(taskDTO, project));

            transaction.commit();
            return true;
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
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.remove(userId, id) != null;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean delete(@Nullable final SessionDTO session, @Nullable final TaskDTO taskDTO) throws Exception {
        if (taskDTO == null) return false;
        return delete(session, taskDTO.getId());
    }

    @Override @NotNull
    public Boolean deleteByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || ids.isEmpty()) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            for (final String id : ids) {
                repository.remove(userId, id);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Collection<String> removeTasksByProjectIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || ids == null || ids.isEmpty()) return Collections.emptyList();

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Collection<String> result = repository.removeByProjectIds(userId, ids);

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null || name == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.removeByName(userId, name).size() > 0;

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
        final TaskRepositoryJPA repository = new TaskRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.removeAll(userId).size() > 0;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
