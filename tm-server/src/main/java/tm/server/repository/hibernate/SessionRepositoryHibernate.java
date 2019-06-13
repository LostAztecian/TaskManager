package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.repository.jpa.SessionRepositoryJPA;
import tm.server.entity.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class SessionRepositoryHibernate implements SessionRepositoryJPA {
    
    private final EntityManager entityManager;

    public SessionRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override @NotNull
    public Collection<Session> findAll() throws Exception {
        final TypedQuery<Session> query = entityManager.createQuery("SELECT s FROM Session s", Session.class);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<Session> findByUserId(@NotNull final String userId) throws Exception {
        final TypedQuery<Session> query = entityManager.createQuery(
                "SELECT s FROM Session s WHERE s.user.id = :userId", Session.class);
        return query.getResultList();
    }

    @Override @Nullable
    public Session findById(@NotNull final String id) throws Exception {
        return entityManager.find(Session.class, id);
    }

    @Override @NotNull
    public Boolean containsId(@NotNull final String id) throws Exception {
        return findById(id) != null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Session session) throws Exception {
        entityManager.persist(session);
        return true;
    }

    @Override @NotNull
    public Boolean deleteById(@NotNull final String id) throws Exception {
        final Session session = entityManager.find(Session.class, id);
        if (session == null) return false;
        entityManager.remove(session);
        return true;
    }

    @Override @NotNull
    public Boolean deleteByUserId(@NotNull final String userId) throws Exception {
        final Query query = entityManager.createQuery(
                "DELETE FROM Session s WHERE s.user.id = :userId", Session.class);
        final int count = query.executeUpdate();
        return count > 0;
    }

    @Override @NotNull
    public Boolean deleteAll() throws Exception {
        final Query query = entityManager.createQuery("DELETE FROM Session s", Session.class);
        final int count = query.executeUpdate();
        return count > 0;
    }
    
}
