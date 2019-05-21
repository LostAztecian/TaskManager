package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.api.Service;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.entity.User;

public class UserServiceImpl extends AbstractService<User> {


    public UserServiceImpl(final @NotNull Repository<User> repository, final @NotNull ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override
    protected @Nullable String getUserId(final @NotNull User object) {
        return null;
    }

    @Override
    protected @Nullable Service<? extends Entity> getChildService() {
        return serviceLocator.getProjectService();
    }

    @Override
    public void save(final User user) {
        if (!isValid(user)) return;
        repository.merge(user);
    }

    private boolean isValid(final User user) {
        if (user == null) return false;
        if (user.getLogin().isEmpty()) return false;
        return !user.getPwdHash().isEmpty();
    }

}
