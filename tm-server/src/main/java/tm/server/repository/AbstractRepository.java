package tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.PlannedEntity;
import tm.server.api.repository.PlannedEntityRepository;
import tm.common.comparator.ComparatorType;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends PlannedEntity> implements PlannedEntityRepository<T> {

    protected @NotNull final Map<String, T> map = new LinkedHashMap<>();

    @Override @NotNull
    public Collection<T> findAll(@NotNull final String userId) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId())).collect(Collectors.toSet());
    }

    @Override @NotNull
    public Collection<T> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) {
        return getSorted(findAll(userId), comparatorType);
    }

    @Override @NotNull
    public Collection<T> findByName(@NotNull final String userId, @NotNull final String name) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId()) && name.equals(e.getName())).collect(Collectors.toSet());
    }

    @Override @NotNull
    public Collection<T> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) {
        return getSorted(findByName(userId, name), comparatorType);
    }

    @Override @Nullable
    public T findOne(@NotNull final String userId, @NotNull final String id) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId()) && e.getId().equals(id)).findAny().orElse(null);
    }

    @Override @NotNull
    public Boolean persist(@NotNull final PlannedEntity object) {
        try {
            map.putIfAbsent(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final PlannedEntity object) {
        if (map.get(object.getId()) != null && userId.equals(map.get(object.getId()).getUserId())) return false;
        try {
            map.put(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        if (map.get(id) == null || !userId.equals(map.get(id).getUserId())) return null;
        map.remove(id);
        return id;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final PlannedEntity object) {
        if (map.get(object.getId()) == null || !userId.equals(map.get(object.getId()).getUserId())) return null;
        map.remove(object.getId());
        return object.getId();
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) {
        final Collection<String> ids = new HashSet<>();
        findAll(userId).stream().filter(e -> name.equals(e.getName()))
                .forEach(e -> {
                    if (map.remove(e.getId()) != null) ids.add(e.getId());
                });
        return ids;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) {
        final Collection<String> ids = new HashSet<>();
        final Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            final T entity = map.get(iterator.next());
            if (!userId.equals(entity.getUserId())) continue;
            iterator.remove();
            ids.add(entity.getId());
        }
        return ids;
    }

    @NotNull
    private Collection<T> getSorted(@NotNull final Collection<T> collection, @NotNull final ComparatorType comparatorType) {
        final TreeSet<PlannedEntity> sortedSet = new TreeSet<PlannedEntity>(comparatorType.comparator);
        final LinkedHashSet<T> result = new LinkedHashSet<>();
        sortedSet.addAll(collection);
        sortedSet.forEach(e -> result.add((T)e));
        return result;
    }

}
