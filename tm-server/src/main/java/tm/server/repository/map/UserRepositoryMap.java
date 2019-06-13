package tm.server.repository.map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.UserDTO;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryMap implements tm.server.api.repository.UserRepository {

    private @NotNull final Map<String, UserDTO> map = new LinkedHashMap<>();

  @NotNull
    public Optional<UserDTO> validate(@NotNull final String login, @NotNull final String pwdHash) {
        return map.values().stream()
                .filter(u -> login.equals(u.getLogin()) && pwdHash.equals(u.getPasswordHash()))
                .findAny();
    }

    @Override @NotNull
    public Collection<UserDTO> findAll(@NotNull final String userId) {
        final UserDTO user = map.get(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        return map.values();
    }

    @Override @NotNull
    public Collection<UserDTO> findByName(@NotNull final String userId, @NotNull final String name) {
        final UserDTO user = map.get(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        return findAll(userId).stream().filter(u -> name.equals(u.getLogin())).collect(Collectors.toSet());
    }

    @Override @Nullable
    public UserDTO findOne(@NotNull final String userId, @NotNull final String id) {
        final UserDTO user = map.get(userId);
        if (user == null) return null;
        return user;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final UserDTO user) {
        return map.putIfAbsent(user.getId(), user) != null;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final UserDTO user) {
        final UserDTO currentUser = map.get(userId);
        if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return false;
        map.put(user.getId(), user);
        return true;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        final UserDTO user = map.get(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return null;
        map.remove(id);
        return id;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final UserDTO user) {
        final UserDTO currentUser = map.get(userId);
        if (currentUser == null || currentUser.getRole() != UserDTO.Role.ADMIN) return null;
        map.remove(user.getId());
        return user.getId();
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) {
        final UserDTO user = map.get(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        final Collection<String> ids =  map.values().stream()
                .filter(u -> name.equals(u.getName())).map(UserDTO::getName).collect(Collectors.toSet());
        ids.forEach(id -> {if (map.remove(id)==null) ids.remove(id);});
        return ids;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) {
        final UserDTO user = map.get(userId);
        if (user == null || user.getRole() != UserDTO.Role.ADMIN) return Collections.emptySet();
        final Collection<String> ids = map.keySet();
        map.clear();
        return ids;
    }

}
