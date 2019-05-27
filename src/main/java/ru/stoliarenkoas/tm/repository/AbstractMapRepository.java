package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.api.repository.PlannedEntityRepository;
import ru.stoliarenkoas.tm.entity.comparator.ComparatorType;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMapRepository<T extends PlannedEntity> implements PlannedEntityRepository<T> {

    protected final @NotNull Map<String, T> map = new LinkedHashMap<>();

    @Override
    public @NotNull Collection<T> findAll(final @NotNull String userId) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId())).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<T> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) {
        return getSorted(findAll(userId), comparatorType);
    }

    @Override
    public @NotNull Collection<T> findByName(final @NotNull String userId, final @NotNull String name) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId()) && name.equals(e.getName())).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<T> findByNameAndSort(final @NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) {
        return getSorted(findByName(userId, name), comparatorType);
    }

    @Override
    public @Nullable T findOne(final @NotNull String userId, final @NotNull String id) {
        return map.values().stream().filter(e -> userId.equals(e.getUserId()) && e.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void persist(final @NotNull PlannedEntity object) {
        try {
            map.putIfAbsent(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void merge(final @NotNull String userId, final @NotNull PlannedEntity object) {
        if (map.get(object.getId()) != null && userId.equals(map.get(object.getId()).getUserId())) return;
        try {
            map.put(object.getId(), (T)object);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public @Nullable String remove(final @NotNull String userId, final @NotNull String id) {
        if (map.get(id) == null || !userId.equals(map.get(id).getUserId())) return null;
        map.remove(id);
        return id;
    }

    @Override
    public @Nullable String remove(final @NotNull String userId, final @NotNull PlannedEntity object) {
        if (map.get(object.getId()) == null || !userId.equals(map.get(object.getId()).getUserId())) return null;
        map.remove(object.getId());
        return object.getId();
    }

    @Override
    public @NotNull Collection<String> removeByName(final @NotNull String userId, final @NotNull String name) {
        final Collection<String> ids = new HashSet<>();
        findAll(userId).stream().filter(e -> name.equals(e.getName()))
                .forEach(e -> {
                    if (map.remove(e.getId()) != null) ids.add(e.getId());
                });
        return ids;
    }

    @Override
    public @NotNull Collection<String> removeAll(final @NotNull String userId) {
        final Collection<String> ids = new HashSet<>();
        map.values().stream().filter(e -> userId.equals(e.getUserId()))
                .forEach(e -> {
                    if (map.remove(e.getId()) != null) ids.add(e.getId());
                });
        return ids;
    }

    private @NotNull Collection<T> getSorted(final @NotNull Collection<T> collection, final @NotNull ComparatorType comparatorType) {
        final TreeSet<PlannedEntity> sortedSet = new TreeSet<PlannedEntity>(comparatorType.comparator);
        final LinkedHashSet<T> result = new LinkedHashSet<>();
        sortedSet.addAll(collection);
        sortedSet.forEach(e -> result.add((T)e));
        return result;
    }

}
