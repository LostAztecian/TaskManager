package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.*;
import tm.server.api.repository.Repository;
import tm.server.api.service.Service;
import tm.server.api.service.UserService;
import tm.common.entity.Project;
import tm.common.entity.User;
import tm.server.repository.UserRepository;
import tm.server.utils.CypherUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    public UserServiceImpl(@NotNull final Repository<User> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @Override @NotNull
    public Boolean save(@Nullable final User user) {
        if (!isValid(user)) return false;
        user.setPasswordHash(CypherUtil.getMd5(user.getPasswordHash()));
        repository.merge(user.getId(), user);
        return true;
    }

    @Override @NotNull
    public Boolean persist(@Nullable final User user) {
        if (!isValid(user)) return false;
        user.setPasswordHash(CypherUtil.getMd5(user.getPasswordHash()));
        repository.persist(user);
        return true;
    }

    @Override @NotNull
    public Boolean deleteChildrenByParentId(@Nullable final String id) {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(Collections.singleton(id));
    }

    @Override @NotNull
    public Boolean deleteChildrenByParentIds(@Nullable final Collection<String> ids) {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(ids);
    }

    @NotNull
    private Boolean isValid(@Nullable final User user) {
        if (user == null) return false;
        if (user.getLogin() == null || user.getLogin().isEmpty()) return false;
        return user.getPasswordHash() != null && !user.getPasswordHash().isEmpty();
    }

    @Override @NotNull
    public Boolean register(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) return false;
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(CypherUtil.getMd5(password));
        return persist(user);
    }

    @Override @Nullable
    public User login(@Nullable final String login, @Nullable final String password) {
        System.out.printf("Login: %s, Password: %s %n%n", login, password);
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        final String passwordHash = CypherUtil.getMd5(password);
        final User user = ((UserRepository)repository).validate(login, passwordHash).orElse(null);
        if (user != null) serviceLocator.setCurrentUser(user);
        return user;
    }

    @Override @NotNull
    public Boolean logout() {
        serviceLocator.setCurrentUser(null);
        return true;
    }

    @Override @NotNull
    public String showUserProfile() {
        final User currentUser = serviceLocator.getCurrentUser();
        if (currentUser == null) return "[YOU ARE NOT LOGGED IN]";
        final StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(currentUser.getLogin()).append("\n");
        sb.append("User status: ").append(currentUser.getRole().getDisplayName()).append("\n");
        sb.append("[TO CHANGE PASSWORD TYPE \'user-change-password\']").append("\n");
        return sb.toString();
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final String oldPassword, @Nullable final String newPassword) {
        final User currentUser = serviceLocator.getCurrentUser();
        if (oldPassword == null || newPassword == null || newPassword.isEmpty()) return false;
        if (currentUser == null || CypherUtil.getMd5(oldPassword).equals(currentUser.getPasswordHash())) return false;
        currentUser.setPasswordHash(CypherUtil.getMd5(newPassword));
        repository.merge(currentUser.getId(), currentUser);
        return true;
    }
}
