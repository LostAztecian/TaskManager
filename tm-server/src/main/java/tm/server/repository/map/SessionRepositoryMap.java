package tm.server.repository.map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionRepositoryMap implements tm.server.api.repository.SessionRepository {

    private final Map<String, Session> map = new LinkedHashMap<>();

    @Override @NotNull
    public Collection<Session> findAll() {
        return map.values();
    }

    @Override @NotNull
    public Collection<Session> findByUserId(@NotNull final String userId) {
        return map.values().stream().filter(s -> userId.equals(s.getUserId())).collect(Collectors.toSet());
    }

    @Override @Nullable
    public Session findById(@NotNull final String id) {
        return map.get(id);
    }

    @Override @NotNull
    public Boolean containsId(@NotNull final String id) {
        return map.containsKey(id);
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Session session) {
        return map.putIfAbsent(session.getId(), session) == null;
    }

    @Override @NotNull
    public Boolean deleteById(@NotNull final String id) {
        return map.remove(id) != null;
    }

    @Override @NotNull
    public Boolean deleteByUserId(@NotNull final String userId) {
        return map.values().removeIf(s -> userId.equals(s.getUserId()));
    }

    @Override @NotNull
    public Boolean deleteAll() {
        map.clear();
        return true;
    }
}
