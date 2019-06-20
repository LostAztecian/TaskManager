package tm.server.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.common.entity.UserDTO;
import tm.server.entity.Session;
import tm.server.entity.User;

public class SessionUtil {

    public static boolean isValid(@NotNull final SessionDTO session) {
        final String sessionHash = session.getHash();
        session.setHash(null);
        final String realSessionHash = CypherUtil.getMd5(session.toString());
        final boolean success = realSessionHash.equals(sessionHash);
        session.setHash(sessionHash);
        return success;
    }

    public static void sign(@NotNull final SessionDTO session) {
        session.setHash(null);
        session.setHash(CypherUtil.getMd5(session.toString()));
    }

    public static void sign(@NotNull final Session session) {
        session.setHash(null);
        session.setHash(CypherUtil.getMd5(session.toString()));
    }

    @Nullable
    public static SessionDTO getSessionForUser(@NotNull final UserDTO user) {
        final String userLogin = user.getLogin();
        if (userLogin == null) return null;
        final SessionDTO session = new SessionDTO(user.getId(), userLogin);
        sign(session);
        return session;
    }

}
