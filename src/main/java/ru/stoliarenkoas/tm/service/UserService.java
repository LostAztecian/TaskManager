package ru.stoliarenkoas.tm.service;

import lombok.RequiredArgsConstructor;
import ru.stoliarenkoas.tm.api.UserRepository;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Collection;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Collection<User> getAll() {
        return repository.findAll();
    }

    public User getByLogin(final String login) {
        if (login == null || login.isEmpty()) return null;
        return repository.findByLogin(login);
    }

    public User getById(final String userId) {
        if (userId == null || userId.isEmpty()) return null;
        return repository.findById(userId);
    }

    public void create(final User user) {
        if (!isValid(user)) return;
        repository.persist(user);
    }

    public void update(final User user) {
        if (!isValid(user)) return;
        repository.merge(user);
    }

    private boolean isValid(final User user) {
        if (user == null) return false;
        if (user.getLogin() == null || user.getLogin().isEmpty()) return false;
        if (user.getPwdHash() == null || user.getPwdHash().isEmpty()) return false;
        if (user.getRole() == null) return false;
        return true;
    }

    public void delete(final String userId) {
        if (userId == null || userId.isEmpty()) return;
        repository.remove(userId);
    }

    public void deleteAll() {
        repository.clear();
    }

}
