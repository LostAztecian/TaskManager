package tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tm.server.entity.Session;

import java.util.List;

@Repository
public interface SessionRepositorySpring extends CrudRepository<Session, String> {

    List<Session> findByUser_Id(@NotNull String userId);

    void deleteById(@NotNull String id);

    int deleteByUser_Id(@NotNull String userId);

    void deleteAll();

}
