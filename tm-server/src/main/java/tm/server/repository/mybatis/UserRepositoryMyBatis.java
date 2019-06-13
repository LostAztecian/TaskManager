package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.UserDTO;
import tm.server.api.repository.UserRepository;
import tm.server.repository.mybatis.mapper.UserMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryMyBatis implements UserRepository {

    private final UserMapper mapper;

    public UserRepositoryMyBatis(@NotNull final SqlSession sqlSession) {
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Override
    public @NotNull Optional<UserDTO> validate(@NotNull String login, @NotNull String pwdHash) throws Exception {
        return Optional.ofNullable(mapper.validate(login, pwdHash));
    }

    @Override
    public @NotNull Collection<UserDTO> findAll(@NotNull String userId) throws Exception {
        final UserDTO user = mapper.findOne(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        return mapper.findAll();
    }

    @Override
    public @NotNull Collection<UserDTO> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        final UserDTO user = mapper.findOne(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        return mapper.findByName(name);
    }

    @Nullable
    @Override
    public UserDTO findOne(@NotNull String userId, @NotNull String id) throws Exception {
        if (!userId.equals(id)) {
            final UserDTO user = mapper.findOne(userId);
            if (user == null || user.getRole() != UserDTO.Role.ADMIN) return null;
        }
        return mapper.findOne(id);
    }

    @Override
    public @NotNull Boolean persist(@NotNull UserDTO user) throws Exception {
        mapper.persist(user);
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull UserDTO user) throws Exception {
        if (!userId.equals(user.getId())) {
            final UserDTO currentUser = mapper.findOne(userId);
            if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return false;
        }
        mapper.remove(user.getId());
        mapper.persist(user);
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final UserDTO currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return null;
        final UserDTO deletedUser = mapper.findOne(id);
        mapper.remove(id);
        return deletedUser.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull UserDTO user) throws Exception {
        return remove(userId, user.getId());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final UserDTO currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        final List<UserDTO> deletedUsers = mapper.findByName(name);
        mapper.removeByName(name);
        return deletedUsers.stream().map(UserDTO::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final UserDTO currentUser = mapper.findOne(userId);
        if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        final List<UserDTO> deletedUsers = mapper.findAll();
        mapper.removeAll();
        return deletedUsers.stream().map(UserDTO::getId).collect(Collectors.toSet());
    }

}
