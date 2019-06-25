package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import tm.common.comparator.ComparatorType;
import tm.server.api.repository.jpa.TaskRepositoryJPA;
import tm.server.entity.Task;
import tm.server.utils.DatabaseUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.HashSet;

public class TaskRepositoryHibernate implements TaskRepositoryJPA {

    private final EntityManager entityManager;

    @Autowired
    public TaskRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override @NotNull
    public Collection<Task> findAll(@NotNull final String userId) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId", Task.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Task> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId ORDER BY :sortColumn", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("sortColumn", DatabaseUtil.getSortColumn(comparatorType));
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Task> findByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId AND t.name = :name", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Task> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId AND t.name = :name ORDER BY :sortColumn", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        query.setParameter("sortColumn", DatabaseUtil.getSortColumn(comparatorType));
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Task> findByProjectId(@NotNull final String userId, @NotNull final String projectId) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId AND t.project.id = :projectId", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("projectId", projectId);
        return query.getResultList();
    }

    @Override @Nullable
    public Task findOne(@NotNull final String userId, @NotNull final String id) throws Exception {
        final Task task = entityManager.find(Task.class, id);
        if (task == null || !userId.equals(task.getUserId())) return null;
        return task;
    }

    @Override @NotNull
    public Collection<Task> search(@NotNull final String userId, @NotNull final String searchLine) throws Exception {
        final TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.user.id = :userId AND " +
                        "(t.name LIKE :searchLine OR t.description LIKE :searchLine)", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("searchLine", "%"+searchLine+"%");
        return query.getResultList();
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Task task) throws Exception {
        entityManager.persist(task);
        return true;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final Task task) throws Exception {
        final Task existingTask = entityManager.find(Task.class, task.getId());
        if (existingTask != null && !userId.equals(existingTask.getUserId())) return false;
        entityManager.merge(task);
        return true;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) throws Exception {
        final Task existingTask = entityManager.find(Task.class, id);
        if (existingTask == null || !userId.equals(existingTask.getUserId())) return null;
        entityManager.remove(existingTask);
        return existingTask.getId();
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final Task task) throws Exception {
        return remove(userId, task.getId());
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final TypedQuery<String> query = entityManager.createQuery(
                "SELECT t.id FROM Task t WHERE t.project.user.id = :userId AND t.name = :name", String.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        final Collection<String> ids = query.getResultList();
        for (final String id : ids) {
            final Query deleteQuery = entityManager.createQuery("DELETE FROM Task t WHERE t.id = id");
            deleteQuery.executeUpdate();
        }
        return ids;
    }

    @Override @NotNull
    public Collection<String> removeByProjectIds(@NotNull final String userId, @NotNull final Collection<String> ids) throws Exception {
        final Collection<String> taskIds = new HashSet<>();
        for (final String id : ids) {
            final TypedQuery<String> query = entityManager.createQuery(
                    "SELECT t.id FROM Task t WHERE t.project.user.id = :userId AND t.project.id = :projectId", String.class);
            query.setParameter("userId", userId);
            query.setParameter("projectId", id);
            taskIds.addAll(query.getResultList());
        }
        for (final String id : taskIds) {
            final Query deleteQuery = entityManager.createQuery("DELETE FROM Task t WHERE t.id = id");
            deleteQuery.executeUpdate();
        }
        return taskIds;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) throws Exception {
        final TypedQuery<String> query = entityManager.createQuery(
                "SELECT t.id FROM Task t WHERE t.project.user.id = :userId", String.class);
        query.setParameter("userId", userId);
        final Collection<String> ids = query.getResultList();

        final Query deleteQuery = entityManager.createQuery("DELETE FROM Task t WHERE t.project.user.id = :userId");
        query.setParameter("userId", userId);
        deleteQuery.executeUpdate();
        return ids;
    }

}
