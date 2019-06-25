package tm.server.repository.deltaspike;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tm.server.entity.Session;

import java.util.List;

@Repository
public interface SessionRepositoryDeltaspike extends CrudRepository<Session, String> {

    public abstract List<Session> findByUser_Id(@NotNull String userId);

    public void deleteById(@NotNull String id);

    public abstract int deleteByUser_Id(@NotNull String userId);

    public void deleteAll();

}
