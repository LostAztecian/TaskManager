package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.server.annotations.Jpa;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.jpa.SessionRepositoryJPA;
import tm.server.api.service.SessionService;
import tm.server.entity.Session;
import tm.server.entity.User;
import tm.server.repository.hibernate.SessionRepositoryHibernate;
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
public class SessionServiceJPA implements SessionService {

    @Inject
    private EntityManagerFactory factory;

    @Inject
    private ServiceLocator serviceLocator;

    public SessionServiceJPA() {
    }

    @Override @NotNull
    public Collection<SessionDTO> getAll() throws Exception {
        final SessionRepositoryJPA repositoryJPA = new SessionRepositoryHibernate(factory.createEntityManager());
        final Collection<Session> sessions = repositoryJPA.findAll();
        return sessions.stream().map(Session::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<SessionDTO> getByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final SessionRepositoryJPA repositoryJPA = new SessionRepositoryHibernate(factory.createEntityManager());
        final Collection<Session> sessions = repositoryJPA.findByUserId(userId);
        return sessions.stream().map(Session::toDTO).collect(Collectors.toList());
    }

    @Override @Nullable
    public SessionDTO getById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return null;
        final SessionRepositoryJPA repositoryJPA = new SessionRepositoryHibernate(factory.createEntityManager());
        final Session session = repositoryJPA.findById(id);
        if (session == null) return null;
        return session.toDTO();
    }

    @Override @NotNull
    public Boolean isOpen(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return false;
        final SessionRepositoryJPA repositoryJPA = new SessionRepositoryHibernate(factory.createEntityManager());
        final Session session = repositoryJPA.findById(id);
        return session != null;
    }

    @Override @NotNull
    public Boolean open(@Nullable SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null || !SessionUtil.isValid(sessionDTO)) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final SessionRepositoryJPA repository = new SessionRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Session session = new Session(sessionDTO);
            final User sessionUser = entityManager.find(User.class, sessionDTO.getUserId());
            if (sessionUser == null) return false;
            session.setUser(sessionUser);
            repository.persist(session);

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeById(@Nullable String id) throws Exception {
        if (id == null || id.isEmpty()) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final SessionRepositoryJPA repository = new SessionRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.deleteById(id);

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeByUserId(@Nullable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final SessionRepositoryJPA repository = new SessionRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.deleteByUserId(userId);

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean closeAll() throws Exception {
        final EntityManager entityManager = factory.createEntityManager();
        final SessionRepositoryJPA repository = new SessionRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.deleteAll();

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
