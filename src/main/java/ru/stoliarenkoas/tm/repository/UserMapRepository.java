package ru.stoliarenkoas.tm.repository;

import ru.stoliarenkoas.tm.api.UserRepository;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserMapRepository implements UserRepository {

    private final Map<String, User> map = new LinkedHashMap<>();

    @Override
    public Collection<User> findAll() {
        return map.values();
    }

    @Override
    public User findById(final String userId) {
        return map.get(userId);
    }

    @Override
    public User findByLogin(final String login) {
        return findAll().stream().filter(u -> u.getLogin().equals(login)).findAny().orElse(null);
    }

    @Override
    public void persist(final User user) {
        map.putIfAbsent(user.getUserId(), user);
    }

    @Override
    public void merge(final User user) {
        map.put(user.getUserId(), user);
    }

    @Override
    public void remove(final String userId) {
        map.remove(userId);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
