package tm.server.service.deltaspike;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;
import tm.server.annotations.Deltaspike;
import tm.server.api.ServiceLocator;
import tm.server.api.service.UserService;
import tm.server.command.user.UserChangePasswordCommand;
import tm.server.repository.deltaspike.UserRepositoryDeltaspike;
import tm.server.utils.CypherUtil;
import tm.server.utils.SessionUtil;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

@Deltaspike
@Transactional
public class UserServiceDeltaspike implements UserService {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private UserRepositoryDeltaspike userRepository;

    @Nullable
    private String getCurrentUserId(@Nullable final SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override
    public @Nullable SessionDTO login(@Nullable String login, @Nullable String password) throws Exception {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;

        final String passwordHash = CypherUtil.getMd5(password);
        final UserDTO userDTO = userRepository.findByLoginEqualAndPasswordHashEqual(login, passwordHash);
        if (userDTO == null) return null;
        final SessionDTO sessionDTO = SessionUtil.getSessionForUser(userDTO);
        serviceLocator.getSessionService().open(sessionDTO);
        return sessionDTO;
    }

    @Override
    public @NotNull Boolean logout(@Nullable SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return false;
        return serviceLocator.getSessionService().closeById(session.getId());
    }

    @Override
    public @NotNull Boolean register(@Nullable String login, @Nullable String password) throws Exception {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) return false;

        final boolean exists = !userRepository.findByLoginEqual(login).isEmpty();
        if (exists) return false;
        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setPasswordHash(CypherUtil.getMd5(password));
        userRepository.persist(userDTO);
        return true;
    }

    @Override
    public @NotNull String showUserProfile(@Nullable SessionDTO sessionDTO) throws Exception {
        final UserDTO currentUser = serviceLocator.getUserService().get(sessionDTO, getCurrentUserId(sessionDTO));
        if (currentUser == null || sessionDTO == null || !SessionUtil.isValid(sessionDTO)) return "[YOU ARE NOT LOGGED IN]";
        return "USER PROFILE:" + "\n" +
                "UserDTO: " + currentUser.getLogin() + "\n" +
                "UserDTO status: " + currentUser.getRole().getDisplayName() + "\n" +
                "[TO CHANGE PASSWORD TYPE" +
                "\'" + UserChangePasswordCommand.NAME + "\']" + "\n";
    }

    @Override
    public @NotNull Boolean changePassword(@Nullable SessionDTO session, @Nullable String oldPassword, @Nullable String newPassword) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;
        final UserDTO userDTO = userRepository.findAnyByIdEqual(userId);
        if (oldPassword == null || newPassword == null || newPassword.isEmpty()) return false;
        if (userDTO == null || !CypherUtil.getMd5(oldPassword).equals(userDTO.getPasswordHash())) return false;
        userDTO.setPasswordHash(CypherUtil.getMd5(newPassword));
        userRepository.save(userDTO);
        return true;
    }

    @Override
    public @NotNull Collection<UserDTO> getAll(@Nullable SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return Collections.emptyList();
        return userRepository.findAll();
    }

    @Override
    public @NotNull Collection<UserDTO> getAllByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        if (session == null || name == null || name.isEmpty() || !SessionUtil.isValid(session)) return Collections.emptyList();
        return userRepository.findByLoginEqual(name);
    }

    @Nullable
    @Override
    public UserDTO get(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        if (session == null || id == null ||
                id.isEmpty() || !SessionUtil.isValid(session)) return null;
        return userRepository.findAnyByIdEqual(id);
    }

    @Override
    public Boolean save(@Nullable SessionDTO session, @Nullable UserDTO object) throws Exception {
        if (session == null || !SessionUtil.isValid(session) || object == null) return false;
        return userRepository.save(object) != null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable String id) throws Exception {
        if (session == null || id == null ||
                id.isEmpty() || !SessionUtil.isValid(session)) return null;
        return userRepository.removeByIdEqual(id) != null;
    }

    @Override
    public Boolean delete(@Nullable SessionDTO session, @Nullable UserDTO object) throws Exception {
        if (session == null || object == null || !SessionUtil.isValid(session)) return null;
        return userRepository.removeByIdEqual(object.getId()) != null;
    }

    @Override
    public Boolean deleteByIds(@Nullable SessionDTO session, @Nullable Collection<String> ids) throws Exception {
        if (session == null || ids == null ||
                ids.isEmpty() || !SessionUtil.isValid(session)) return null;
        for (final String id : ids) {
            userRepository.removeByIdEqual(id);
        }
        return true;
    }

    @Override
    public Boolean deleteByName(@Nullable SessionDTO session, @Nullable String name) throws Exception {
        if (session == null || name == null ||
                name.isEmpty() || !SessionUtil.isValid(session)) return null;
        return !userRepository.removeByNameEqual(name).isEmpty();
    }

    @Override
    public Boolean deleteAll(@Nullable SessionDTO session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        return !userRepository.removeAll().isEmpty();
    }
}
