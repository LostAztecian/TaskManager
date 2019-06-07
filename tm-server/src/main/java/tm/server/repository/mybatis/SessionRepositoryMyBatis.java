package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.repository.SessionRepository;
import tm.server.repository.mybatis.mapper.SessionMapper;

import java.util.Collection;

public class SessionRepositoryMyBatis implements SessionRepository {

    private final SqlSession session;
    private final SessionMapper mapper;

    public SessionRepositoryMyBatis(SqlSessionFactory sessionFactory) {
        session = sessionFactory.openSession();
        session.getConfiguration().addMapper(SessionMapper.class);
        mapper = session.getMapper(SessionMapper.class);
    }

    @Override
    public @NotNull Collection<Session> findAll() throws Exception {
        return mapper.findAll();
    }

    @Override
    public @NotNull Collection<Session> findByUserId(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @Nullable Session findById(@NotNull String id) throws Exception {
        return mapper.findById(id);
    }

    @Override
    public @NotNull Boolean containsId(@NotNull String id) throws Exception {
        return mapper.findById(id) != null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull Session session) throws Exception {
        mapper.persist(session);
        this.session.commit();
        return true;
    }

    @Override
    public @NotNull Boolean deleteById(@NotNull String id) throws Exception {
        mapper.removeById(id);
        return true;
    }

    @Override
    public @NotNull Boolean deleteByUserId(@NotNull String userId) throws Exception {
        mapper.removeByUserId(userId);
        return true;
    }

    @Override
    public @NotNull Boolean deleteAll() throws Exception {
        mapper.removeAll();
        return true;
    }

}
