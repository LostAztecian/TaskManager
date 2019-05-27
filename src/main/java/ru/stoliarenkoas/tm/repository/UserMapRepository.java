package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.repository.UserRepository;
import ru.stoliarenkoas.tm.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserMapRepository implements UserRepository {

    private final @NotNull Map<String, User> map = new LinkedHashMap<>();

    public @NotNull Optional<User> validate(final @NotNull String login, final @NotNull String pwdHash) {
        final Optional<User> user = map.values().stream()
                .filter(u -> login.equals(u.getLogin()) && pwdHash.equals(u.getPwdHash()))
                .findAny();
        return user;
    }

    @Override
    public @NotNull Collection<User> findAll(@NotNull String userId) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        return map.values();
    }

    @Override
    public @NotNull Collection<User> findByName(@NotNull String userId, @NotNull String name) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        return findAll(userId).stream().filter(u -> name.equals(u.getLogin())).collect(Collectors.toSet());
    }

    @Override
    public User findOne(@NotNull String userId, @NotNull String id) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return null;
        return user;
    }

    @Override
    public void persist(User user) {
        map.putIfAbsent(user.getId(), user);
    }

    @Override
    public void merge(@NotNull String userId, User user) {
        final User currentUser = map.get(userId);
        if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return;
        map.put(user.getId(), user);
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return null;
        map.remove(id);
        return id;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, User user) {
        final User currentUser = map.get(userId);
        if (currentUser == null || currentUser.getRole() != User.Role.ADMIN) return null;
        map.remove(user.getId());
        return user.getId();
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        final Collection<String> ids =  map.values().stream()
                .filter(u -> name.equals(u.getName())).map(User::getName).collect(Collectors.toSet());
        ids.forEach(id -> {if (map.remove(id)==null) ids.remove(id);});
        return ids;
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) {
        final User user = map.get(userId);
        if (user == null || user.getRole() != User.Role.ADMIN) return Collections.emptySet();
        final Collection<String> ids = map.keySet();
        map.clear();
        return ids;
    }

}
