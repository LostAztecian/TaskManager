package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.server.api.repository.jpa.ProjectRepositoryJPA;
import tm.server.entity.Project;
import tm.server.utils.DatabaseUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class ProjectRepositoryHibernate implements ProjectRepositoryJPA {

    private final EntityManager entityManager;

    public ProjectRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override @NotNull
    public Collection<Project> findAll(@NotNull final String userId) throws Exception {
        final TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.user.id = :userId", Project.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Project> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) throws Exception {
        final TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.user.id = :userId ORDER BY :sortMethod", Project.class);
        query.setParameter("userId", userId);
        query.setParameter("sortMethod", DatabaseUtil.getSortColumn(comparatorType));
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Project> findByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.user.id = :userId AND p.name = :name", Project.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Project> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) throws Exception {
        final TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.user.id = :userId AND p.name = :name ORDER BY :sortMethod", Project.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        query.setParameter("sortMethod", DatabaseUtil.getSortColumn(comparatorType));
        return query.getResultList();
    }

    @Override @Nullable
    public Project findOne(@NotNull final String userId, @NotNull final String id) throws Exception {
        final Project project = entityManager.find(Project.class, id);
        if (project == null || !userId.equals(project.getUserId())) return null;
        return project;
    }

    @Override @NotNull
    public Collection<Project> search(@NotNull final String userId, @NotNull final String searchLine) throws Exception {
        final TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.user.id = :userId AND " +
                        "(p.name LIKE :searchLine OR p.description LIKE :searchLine)", Project.class);
        query.setParameter("userId", userId);
        query.setParameter("searchLine", "%"+searchLine+"%");
        return query.getResultList();
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Project project) throws Exception {
        entityManager.persist(project);
        return true;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final Project project) throws Exception {
        final Project existingProject = entityManager.find(Project.class, project.getId());
        if (existingProject != null && !userId.equals(existingProject.getUserId())) return false;
        entityManager.merge(project);
        return true;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) throws Exception {
        final Project existingProject = entityManager.find(Project.class, id);
        if (existingProject == null || !userId.equals(existingProject.getUserId())) return null;
        entityManager.remove(existingProject);
        return existingProject.getId();
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final Project project) throws Exception {
        return remove(userId, project.getId());
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final TypedQuery<String> query = entityManager.createQuery(
                "SELECT p.id FROM Project p WHERE p.user.id = :userId AND p.name = :name", String.class);
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        final Collection<String> ids = query.getResultList();
        for (final String id : ids) {
            final Query deleteQuery = entityManager.createQuery("DELETE FROM Project p WHERE p.id = id");
            deleteQuery.executeUpdate();
        }
        return ids;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) throws Exception {
        final TypedQuery<String> findQuery = entityManager.createQuery(
                "SELECT p.id FROM Project p WHERE p.user.id = :userId", String.class);
        findQuery.setParameter("userId", userId);
        final Collection<String> ids = findQuery.getResultList();

        final Query deleteQuery = entityManager.createQuery("DELETE FROM Project p WHERE p.user.id = :userId");
        deleteQuery.setParameter("userId", userId);
        deleteQuery.executeUpdate();
        return ids;
    }
}
