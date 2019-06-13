package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.SessionDTO;
import tm.server.api.repository.SessionRepository;
import tm.server.repository.mybatis.mapper.SessionMapper;

import java.util.Collection;

public class SessionRepositoryMyBatis implements SessionRepository {

    private final SessionMapper mapper;

    public SessionRepositoryMyBatis(@NotNull final SqlSession sqlSession) {
        mapper = sqlSession.getMapper(SessionMapper.class);
    }

    @Override
    public @NotNull Collection<SessionDTO> findAll() throws Exception {
        return mapper.findAll();
    }

    @Override
    public @NotNull Collection<SessionDTO> findByUserId(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @Nullable SessionDTO findById(@NotNull String id) throws Exception {
        return mapper.findById(id);
    }

    @Override
    public @NotNull Boolean containsId(@NotNull String id) throws Exception {
        return mapper.findById(id) != null;
    }

    @Override
    public @NotNull Boolean persist(@NotNull SessionDTO session) throws Exception {
        mapper.persist(session);
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
