package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.*;
import tm.server.api.repository.Repository;
import tm.server.api.repository.UserRepository;
import tm.server.api.service.Service;
import tm.server.api.service.UserService;
import tm.common.entity.Project;
import tm.common.entity.User;
import tm.server.command.user.UserChangePasswordCommand;
import tm.server.repository.map.UserRepositoryMap;
import tm.server.utils.CypherUtil;
import tm.server.utils.SessionUtil;

import java.util.Collection;
import java.util.Collections;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    public UserServiceImpl(@NotNull final Repository<User> repository, @NotNull final ServiceLocator serviceLocator) {
        super(repository, serviceLocator);
    }

    @NotNull
    private Boolean isValid(@Nullable final Session session, @Nullable final User user) throws Exception {
        if (session == null || user == null) return false;
        if (user.getLogin() == null || user.getLogin().isEmpty()) return false;
        if (!session.getUserId().equals(user.getId())) return false;
        return user.getPasswordHash() != null && !user.getPasswordHash().isEmpty();
    }

    @Override @NotNull
    public Boolean save(@Nullable final Session session, @Nullable final User user) throws Exception {
        try {
            if (!isValid(session, user)) return false;
            user.setPasswordHash(CypherUtil.getMd5(user.getPasswordHash())); //checked in validation method
            repository.merge(user.getId(), user);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @NotNull
    public Boolean persist(@Nullable final Session session, @Nullable final User user) throws Exception {
        try {
            if (!isValid(session, user)) return false;
            repository.persist(user); //checked in validation method
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @NotNull
    public Boolean deleteChildrenByParentId(@Nullable final Session session, @Nullable final String id) throws Exception {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(session, Collections.singleton(id));
    }

    @Override @NotNull
    public Boolean deleteChildrenByParentIds(@Nullable final Session session, @Nullable final Collection<String> ids) throws Exception {
        final Service<Project> childService = serviceLocator.getProjectService();
        return childService.deleteByIds(session, ids);
    }

    @Override @NotNull
    public Boolean register(@Nullable final String login, @Nullable final String password) throws Exception {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) return false;
        try {
            final User user = new User();
            user.setLogin(login);
            user.setPasswordHash(CypherUtil.getMd5(password));
            final Session session = SessionUtil.getSessionForUser(user);
            final Boolean result = persist(session, user);
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override @Nullable
    public Session login(@Nullable final String login, @Nullable final String password) throws Exception {
        System.out.printf("[AUTH] Login: %s, Password: %s %n", login, password);
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        final String passwordHash = CypherUtil.getMd5(password);
        final User user = ((UserRepository)repository).validate(login, passwordHash).orElse(null);
        if (user == null) return null;
        final Session session = SessionUtil.getSessionForUser(user);
        serviceLocator.getSessionService().open(session);
        return session;
    }

    @Override
    public @NotNull Boolean logout(@Nullable final Session session) throws Exception {
        if (session == null) return false;
        return serviceLocator.getSessionService().closeById(session.getId());
    }

    @Override @NotNull
    public String showUserProfile(@Nullable final Session session) throws Exception {
        final User currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
        if (currentUser == null) return "[YOU ARE NOT LOGGED IN]";
        return "USER PROFILE:" + "\n" +
                "User: " + currentUser.getLogin() + "\n" +
                "User status: " + currentUser.getRole().getDisplayName() + "\n" +
                "[TO CHANGE PASSWORD TYPE" +
                "\'" + UserChangePasswordCommand.NAME + "\']" + "\n";
    }

    @Override @NotNull
    public Boolean changePassword(@Nullable final Session session, @Nullable final String oldPassword, @Nullable final String newPassword) throws Exception {
        try {
            final User currentUser = serviceLocator.getUserService().get(session, getCurrentUserId(session));
            if (oldPassword == null || newPassword == null || newPassword.isEmpty()) return false;
            if (currentUser == null || CypherUtil.getMd5(oldPassword).equals(currentUser.getPasswordHash())) return false;
            currentUser.setPasswordHash(CypherUtil.getMd5(newPassword));
            repository.merge(currentUser.getId(), currentUser);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
}
