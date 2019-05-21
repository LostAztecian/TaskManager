package ru.stoliarenkoas.tm.api;

import ru.stoliarenkoas.tm.entity.User;

import java.util.Collection;

public interface UserRepository {

    Collection<User> findAll();

    User findById(final String userId);

    User findByLogin(final String login);

    void persist(final User user);

    void merge(final User user);

    void remove(final String userId);

    void clear();

}
