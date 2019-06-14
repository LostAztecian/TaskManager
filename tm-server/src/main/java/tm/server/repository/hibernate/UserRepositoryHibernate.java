package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.UserDTO;
import tm.server.api.repository.jpa.UserRepositoryJPA;
import tm.server.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryHibernate implements UserRepositoryJPA {
    
    @NotNull
    private final EntityManager entityManager;

    public UserRepositoryHibernate(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override @NotNull
    public Optional<User> validate(@NotNull final String login, @NotNull final String pwdHash) throws Exception {
        final TypedQuery<User> query = entityManager.createQuery(
                "SELECT e FROM User e WHERE e.login = :login AND e.passwordHash = :pwdHash", User.class);
        query.setParameter("login", login);
        query.setParameter("pwdHash", pwdHash);
        return query.getResultStream().findAny();
    }

    @Override @NotNull
    public Collection<User> findAll(@NotNull final String userId) throws Exception {
        final User user = entityManager.find(User.class, userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptyList();
        final TypedQuery<User> query = entityManager.createQuery("SELECT e FROM User e", User.class);
        return query.getResultList();
    }

    @Override @NotNull
    public Collection<User> findByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final TypedQuery<User> query = entityManager.createQuery(
                "SELECT e FROM User e WHERE e.login = :name", User.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override @Nullable
    public User findOne(@NotNull final String userId, @NotNull final String id) throws Exception {
        if (!userId.equals(id)) {
            final User user = entityManager.find(User.class, userId);
            if (user.getRole() != UserDTO.Role.ADMIN) return null;
        }
        return entityManager.find(User.class, id);
    }

    @Override @NotNull
    public Boolean persist(@NotNull final User user) throws Exception {
        entityManager.persist(user);
        return true;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final User user) throws Exception {
        if (!userId.equals(user.getId())) {
            final User currentUser = entityManager.find(User.class, userId);
            if (currentUser.getRole() != UserDTO.Role.ADMIN) return false;
        }
        entityManager.merge(user);
        return true;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) throws Exception {
        if (!userId.equals(id)) {
            final User currentUser = entityManager.find(User.class, userId);
            if (currentUser.getRole() != UserDTO.Role.ADMIN) return null;
        }
        final User user = entityManager.find(User.class, id);
        if (user == null) return null;
        entityManager.remove(user);
        return user.getId();
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final User user) throws Exception {
        return remove(userId, user.getId());
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) throws Exception {
        final User currentUser = entityManager.find(User.class, userId);
        if (currentUser.getRole() != UserDTO.Role.ADMIN) return Collections.emptyList();

        final TypedQuery<User> findQuery = entityManager.createQuery(
                "SELECT e FROM User e WHERE e.login = :name", User.class);
        findQuery.setParameter("name", name);
        final List<User> users = findQuery.getResultList();
        for (final User user : users) {
            entityManager.remove(user);
        }
        return users.stream().map(User::getId).collect(Collectors.toList());
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) throws Exception {
        final User currentUser = entityManager.find(User.class, userId);
        if (currentUser.getRole() != UserDTO.Role.ADMIN) return Collections.emptyList();

        final TypedQuery<User> findQuery = entityManager.createQuery(
                "SELECT e FROM User e", User.class);
        final List<User> users = findQuery.getResultList();
        for (final User user : users) {
            entityManager.remove(user);
        }
        return users.stream().map(User::getId).collect(Collectors.toList());
    }
}
