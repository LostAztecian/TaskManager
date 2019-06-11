package tm.server.repository.hibernate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.repository.UserRepository;
import tm.server.graph.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Optional;

public class UserRepositoryHibernate implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public @NotNull Optional<User> validate(@NotNull String login, @NotNull String pwdHash) throws Exception {
        final Query query = entityManager.createQuery("SELECT e FROM tm.server.graph.User e WHERE e.login = :login");
        query.setParameter("login", login);
        final Optional<User> user = query.getResultStream().findAny();

        return user;
    }

    @Override
    public @NotNull Collection<User> findAll(@NotNull String userId) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<User> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public User findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull User object) throws Exception {
        return null;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull User object) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull User object) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        return null;
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        return null;
    }

}
