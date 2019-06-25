package tm.server.service.jpa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;
import tm.server.annotations.Jpa;
import tm.server.api.ServiceLocator;
import tm.server.api.repository.jpa.UserRepositoryJPA;
import tm.server.api.service.UserService;
import tm.server.command.user.UserChangePasswordCommand;
import tm.server.entity.User;
import tm.server.repository.hibernate.UserRepositoryHibernate;
import tm.server.utils.CypherUtil;
import tm.server.utils.SessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Jpa
@SuppressWarnings("Duplicates")
@Service
public class UserServiceJPA implements UserService {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private EntityManagerFactory factory;

    public UserServiceJPA() {
    }

    @NotNull
    private Boolean isValid(@Nullable final SessionDTO session, @Nullable final UserDTO user) throws Exception {
        if (session == null || user == null) return false;
        if (user.getLogin() == null || user.getLogin().isEmpty()) return false;
        if (!session.getUserId().equals(user.getId())) return false;
        return user.getPasswordHash() != null && !user.getPasswordHash().isEmpty();
    }

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @Nullable
    public SessionDTO login(@Nullable final String login, @Nullable final String password) throws Exception {
        System.out.printf("[AUTH] Login: %s, Password: %s %n", login, password);
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;

        final String passwordHash = CypherUtil.getMd5(password);
        final UserRepositoryJPA repository = new UserRepositoryHibernate(factory.createEntityManager());
        final User user = repository.validate(login, passwordHash).orElse(null);
        if (user == null) return null;
        final UserDTO userDTO = user.toDTO();
        final SessionDTO session = SessionUtil.getSessionForUser(userDTO);
        serviceLocator.getSessionService().open(session);
        return session;
    }

    @Override @NotNull
    public Boolean logout(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return false;
        return serviceLocator.getSessionService().closeById(session.getId());
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) throws Exception {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) return false;
        
        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            
            final User user = new User();
            user.setLogin(login);
            user.setPasswordHash(CypherUtil.getMd5(password));
            final Boolean result = repository.persist(user);
            
            transaction.commit();
            return result;
        } catch (PersistenceException exists) {
            transaction.rollback();
            return false;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public String showUserProfile(@Nullable final SessionDTO session) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null || session == null || SessionUtil.isValid(session)) return "[YOU ARE NOT LOGGED IN]";
        return "USER PROFILE:" + "\n" +
                "UserDTO: " + currentUser.getLogin() + "\n" +
                "UserDTO status: " + currentUser.getRole().getDisplayName() + "\n" +
                "[TO CHANGE PASSWORD TYPE" +
                "\'" + UserChangePasswordCommand.NAME + "\']" + "\n";
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final SessionDTO session, @Nullable final String oldPassword, @Nullable final String newPassword) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final UserDTO currentUser = get(session, getCurrentUserId(session));
            if (oldPassword == null || newPassword == null || newPassword.isEmpty()) return false;
            if (currentUser == null || CypherUtil.getMd5(oldPassword).equals(currentUser.getPasswordHash())) return false;
            currentUser.setPasswordHash(CypherUtil.getMd5(newPassword));
            repository.merge(currentUser.getId(), new User(currentUser));

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Collection<UserDTO> getAll(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return Collections.emptyList();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(factory.createEntityManager());
        final String currentUserId = getCurrentUserId(session);
        if (currentUserId == null) return Collections.emptyList();
        return repository.findAll(currentUserId).stream().map(User::toDTO).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<UserDTO> getAllByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || name == null) return Collections.emptyList();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(factory.createEntityManager());
        final String currentUserId = getCurrentUserId(session);
        if (currentUserId == null) return Collections.emptyList();
        return repository.findByName(currentUserId, name).stream().map(User::toDTO).collect(Collectors.toList());
    }


    @Override @Nullable
    public UserDTO get(@Nullable final SessionDTO session, @Nullable final String id) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || id == null) return null;
        System.out.println("CHECK!");
        final UserRepositoryJPA repository = new UserRepositoryHibernate(factory.createEntityManager());
        final String currentUserId = getCurrentUserId(session);
        if (currentUserId == null) return null;
        final User user = repository.findOne(currentUserId, id);
        if (user == null) return null;
        return user.toDTO();
    }

    @Override @NotNull
    public Boolean save(@Nullable final SessionDTO session, @Nullable final UserDTO userDTO) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || userDTO == null) return false;
        if (!isValid(session, userDTO)) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            userDTO.setPasswordHash(CypherUtil.getMd5(userDTO.getPasswordHash())); //checked in validation method
            final Boolean result = repository.merge(userId, new User(userDTO));

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean delete(@Nullable final SessionDTO session, @Nullable final String id) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || id == null) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
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
    public Boolean delete(@Nullable final SessionDTO session, @Nullable final UserDTO userDTO) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || userDTO == null) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            final Boolean result = repository.remove(userId, userDTO.getId()) != null;

            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteByIds(@Nullable final SessionDTO session, @Nullable final Collection<String> ids) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || ids == null || ids.isEmpty()) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
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
    public Boolean deleteByName(@Nullable final SessionDTO session, @Nullable final String name) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || name == null) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
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
        if (session == null || !SessionUtil.isValid(session)) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;

        final EntityManager entityManager = factory.createEntityManager();
        final UserRepositoryJPA repository = new UserRepositoryHibernate(entityManager);
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
