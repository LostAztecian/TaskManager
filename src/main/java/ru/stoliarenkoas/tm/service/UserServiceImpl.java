package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.*;
import ru.stoliarenkoas.tm.entity.User;
import ru.stoliarenkoas.tm.repository.UserMapRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    public UserServiceImpl(final @NotNull Repository<User> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    protected @Nullable Service<? extends Entity> getChildService() {
        return serviceLocator.getProjectService();
    }

    public @NotNull Optional<User> validate(final @Nullable String name, final @Nullable String pwdHash) {
        if (name == null || name.isEmpty()) return Optional.ofNullable(null);
        if (pwdHash == null || pwdHash.isEmpty()) return Optional.ofNullable(null);
        return ((UserMapRepository)repository).validate(name, pwdHash);
    }

    @Override
    public void save(final User user) {
        if (!isValid(user)) return;
        repository.merge(user.getUserId(), user);
    }

    public void persist(final @Nullable User user) {
        if (!isValid(user)) return;
        repository.persist(user);
    }

    private boolean isValid(final User user) {
        if (user == null) return false;
        if (user.getLogin().isEmpty()) return false;
        return !user.getPwdHash().isEmpty();
    }

}
