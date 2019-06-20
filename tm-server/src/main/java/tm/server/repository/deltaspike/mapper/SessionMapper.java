package tm.server.repository.deltaspike.mapper;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import tm.common.entity.SessionDTO;
import tm.server.entity.Session;
import tm.server.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class SessionMapper extends SimpleQueryInOutMapperBase<Session, SessionDTO> {

    @Inject
    private EntityManager entityManager;

    @Override
    protected Object getPrimaryKey(SessionDTO sessionDTO) {
        return sessionDTO.getId();
    }

    @Override
    protected SessionDTO toDto(Session session) {
        final User user = session.getUser();
        final SessionDTO dto = new SessionDTO();
        dto.setId(session.getId());
        dto.setUserId(user.getId());
        dto.setUserLogin(user.getLogin() == null ? "no login" : user.getLogin());
        dto.setHash(session.getHash());
        dto.setCreationDate(session.getCreationDate());
        dto.setSortMethod(session.getSortMethod());
        return dto;
    }

    @Override
    protected Session toEntity(Session session, SessionDTO sessionDTO) {
        session.setId(sessionDTO.getId());
        final User user = entityManager.find(User.class, sessionDTO.getUserId());
        System.out.println("to User entity: " + user);
        session.setUser(user);
        session.setHash(sessionDTO.getHash());
        session.setCreationDate(sessionDTO.getCreationDate());
        session.setSortMethod(sessionDTO.getSortMethod());
        return session;
    }
}
