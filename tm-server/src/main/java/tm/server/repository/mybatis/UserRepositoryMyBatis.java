package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.User;
import tm.server.api.repository.UserRepository;
import tm.server.repository.mybatis.mapper.UserMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryMyBatis implements UserRepository {

    private final SqlSession session;
    private final UserMapper mapper;

    public UserRepositoryMyBatis(SqlSessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
        session.getConfiguration().addMapper(UserMapper.class);
        mapper = session.getMapper(UserMapper.class);
    }

    @Override
    public @NotNull Optional<User> validate(@NotNull String login, @NotNull String pwdHash) throws Exception {
        return Optional.ofNullable(mapper.validate(login, pwdHash));
    }

    @Override
    public @NotNull Collection<User> findAll(@NotNull String userId) throws Exception {
        final User user = mapper.findOne(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        return mapper.findAll();
    }

    @Override
    public @NotNull Collection<User> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        final User user = mapper.findOne(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        return mapper.findByName(name);
    }

    @Nullable
    @Override
    public User findOne(@NotNull String userId, @NotNull String id) throws Exception {
        if (!userId.equals(id)) {
            final User user = mapper.findOne(userId);
            if (user == null || user.getRole() != User.Role.ADMIN) return null;
        }
        return mapper.findOne(id);
    }

    @Override
    public @NotNull Boolean persist(@NotNull User user) throws Exception {
        mapper.persist(user);
        session.commit();
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull User user) throws Exception {
        if (!userId.equals(user.getId())) {
            final User currentUser = mapper.findOne(userId);
            if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return false;
        }
        mapper.remove(user.getId());
        mapper.persist(user);
        session.commit();
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final User currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return null;
        final User deletedUser = mapper.findOne(id);
        mapper.remove(id);
        session.commit();
        return deletedUser.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull User user) throws Exception {
        return remove(userId, user.getId());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final User currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return Collections.emptySet();
        final List<User> deletedUsers = mapper.findByName(name);
        mapper.removeByName(name);
        session.commit();
        return deletedUsers.stream().map(User::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final User currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return Collections.emptySet();
        final List<User> deletedUsers = mapper.findAll();
        mapper.removeAll();
        session.commit();
        return deletedUsers.stream().map(User::getId).collect(Collectors.toSet());
    }
}
