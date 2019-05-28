package tm.client.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.api.ServiceLocator;
import tm.client.api.repository.Repository;
import tm.client.api.service.Service;
import tm.client.api.service.UserService;
import tm.client.entity.Project;
import tm.client.entity.User;
import tm.client.repository.UserRepository;

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
    public void save(@Nullable final User user) {
        if (!isValid(user)) return;
        repository.merge(user.getId(), user);
    }

    @Override
    public void persist(@Nullable final User user) {
        if (!isValid(user)) return;
        repository.persist(user);
    }

    @Override
    public void deleteChildrenByParentId(@Nullable final String id) {
        final Service<Project> childService = serviceLocator.getProjectService();
        childService.deleteByIds(Collections.singleton(id));
    }

    @Override
    public void deleteChildrenByParentIds(@Nullable final Collection<String> ids) {
        final Service<Project> childService = serviceLocator.getProjectService();
        childService.deleteByIds(ids);
    }

    private boolean isValid(@Nullable final User user) {
        if (user == null) return false;
        if (user.getLogin().isEmpty()) return false;
        return !user.getPasswordHash().isEmpty();
    }

}
