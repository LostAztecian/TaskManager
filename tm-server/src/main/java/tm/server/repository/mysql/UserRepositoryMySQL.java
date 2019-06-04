package tm.server.repository.mysql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.User;
import tm.server.api.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public class UserRepositoryMySQL implements UserRepository {

    @Override @NotNull
    public Optional<User> validate(@NotNull final String login, @NotNull final String pwdHash) {
        return Optional.empty();
    }

    @Override @NotNull
    public Collection<User> findAll(@NotNull final String userId) {
        return null;
    }

    @Override @NotNull
    public Collection<User> findByName(@NotNull final String userId, @NotNull final String name) {
        return null;
    }

    @Override @Nullable
    public User findOne(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final User object) {
        return null;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final User object) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final User object) {
        return null;
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) {
        return null;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) {
        return null;
    }
}
