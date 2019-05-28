package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.ServiceLocator;
import tm.server.api.entity.Entity;
import tm.server.api.repository.Repository;
import tm.server.api.service.Service;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    @NotNull
    protected final Repository<T> repository;
    @NotNull
    final ServiceLocator serviceLocator;

    AbstractService(@NotNull final Repository<T> repository,
                           @NotNull final ServiceLocator serviceLocator) {
        this.repository = repository;
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    protected String getCurrentUserId() {
        if (serviceLocator.getCurrentUser() == null) return null;
        return serviceLocator.getCurrentUser().getId();
    }

    @Override @NotNull
    public Collection<T> getAll() {
        final String userId = getCurrentUserId();
        if (userId == null) return Collections.emptySet();
        return repository.findAll(userId);
    }

    @Override @NotNull
    public Collection<T> getAllByName(@Nullable final String name) {
        final String userId = getCurrentUserId();
        if (name == null || name.isEmpty() || userId == null) return Collections.emptySet();
        return repository.findByName(userId, name);
    }

    @Override @Nullable
    public T get(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final String userId = getCurrentUserId();
        if (userId == null) return null;
        return repository.findOne(userId, id);
    }

    @Override
    public void save(@Nullable final T object) {
        final String userId = getCurrentUserId();
        if (userId == null) return;
        if (object == null || object.getId().isEmpty()) return;
        repository.merge(userId, object);
    }

    @Override
    public void delete(@Nullable final String id) {
        if (id == null || id.isEmpty() || get(id) == null) return;
        final String userId = getCurrentUserId();
        if (userId == null) return;
        final String deletedId = repository.remove(userId, id);
        deleteChildrenByParentId(deletedId);
    }

    @Override
    public void delete(@Nullable final T object) {
        final String userId = getCurrentUserId();
        if (userId == null || object == null) return;
        final String deletedId = repository.remove(userId, object);
        deleteChildrenByParentId(deletedId);
    }

    @Override
    public abstract void deleteChildrenByParentId(@Nullable final String id);

    @Override
    public abstract void deleteChildrenByParentIds(@Nullable final Collection<String> ids);

    @Override
    public void deleteByName(@Nullable final String name) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null || name.isEmpty()) return;
        final Collection<String> deletedIds = repository.removeByName(userId, name);
        deleteChildrenByParentIds(deletedIds);
    }

    @Override
    public void deleteByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        ids.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        final String userId = getCurrentUserId();
        if (userId == null) return;
        final Collection<String> deletedIds = repository.removeAll(userId);
        deleteChildrenByParentIds(deletedIds);
    }

}
