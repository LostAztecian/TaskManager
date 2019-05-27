package ru.stoliarenkoas.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.*;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.api.repository.PlannedEntityRepository;
import ru.stoliarenkoas.tm.api.repository.Repository;
import ru.stoliarenkoas.tm.api.service.Service;
import ru.stoliarenkoas.tm.api.service.UserService;
import ru.stoliarenkoas.tm.entity.Project;
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
    public @NotNull Optional<User> validate(final @Nullable String name, final @Nullable String pwdHash) {
        if (name == null || name.isEmpty()) return Optional.ofNullable(null);
        if (pwdHash == null || pwdHash.isEmpty()) return Optional.ofNullable(null);
        return ((UserMapRepository)repository).validate(name, pwdHash);
    }

    @Override
    public void save(final User user) {
        if (!isValid(user)) return;
        repository.merge(user.getId(), user);
    }

    @Override
    public void persist(final @Nullable User user) {
        if (!isValid(user)) return;
        repository.persist(user);
    }

    @Override
    public void deleteChildrenByParentId(final @Nullable String id) {
        final Service<Project> childService = serviceLocator.getProjectService();
        childService.deleteByIds(Collections.singleton(id));
    }

    @Override
    public void deleteChildrenByParentIds(final @Nullable Collection<String> ids) {
        final Service<Project> childService = serviceLocator.getProjectService();
        childService.deleteByIds(ids);
    }

    private boolean isValid(final User user) {
        if (user == null) return false;
        if (user.getLogin().isEmpty()) return false;
        return !user.getPwdHash().isEmpty();
    }

}
