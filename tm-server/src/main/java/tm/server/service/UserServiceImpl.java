package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.*;
import tm.server.api.repository.Repository;
import tm.server.api.service.Service;
import tm.server.api.service.UserService;
import tm.server.entity.Project;
import tm.server.entity.User;
import tm.server.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    public UserServiceImpl(@NotNull final Repository<User> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override @NotNull
    public Optional<User> validate(@Nullable final String name, @Nullable final String pwdHash) {
        if (name == null || name.isEmpty()) return Optional.ofNullable(null);
        if (pwdHash == null || pwdHash.isEmpty()) return Optional.ofNullable(null);
        return ((UserRepository)repository).validate(name, pwdHash);
    }

    @Override
    public Boolean save(@Nullable final User user) {
        if (!isValid(user)) return false;
        repository.merge(user.getId(), user);
        return true;
    }

    @Override
    public Boolean persist(@Nullable final User user) {
        if (!isValid(user)) return false;
        repository.persist(user);
        return true;
    }

    @Override
    public Boolean deleteChildrenByParentId(@Nullable final String id) {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(Collections.singleton(id));
    }

    @Override
    public Boolean deleteChildrenByParentIds(@Nullable final Collection<String> ids) {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(ids);
    }

    private Boolean isValid(@Nullable final User user) {
        if (user == null) return false;
        if (user.getLogin().isEmpty()) return false;
        return !user.getPasswordHash().isEmpty();
    }

}
