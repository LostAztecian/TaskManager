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
    public Boolean save(@Nullable final T object) {
        final String userId = getCurrentUserId();
        if (userId == null) return false;
        if (object == null || object.getId().isEmpty()) return false;
        repository.merge(userId, object);
        return true;
    }

    @Override
    public Boolean delete(@Nullable final String id) {
        if (id == null || id.isEmpty() || get(id) == null) return false;
        final String userId = getCurrentUserId();
        if (userId == null) return false;
        final String deletedId = repository.remove(userId, id);
        deleteChildrenByParentId(deletedId);
        return true;
    }

    @Override
    public Boolean delete(@Nullable final T object) {
        final String userId = getCurrentUserId();
        if (userId == null || object == null) return false;
        final String deletedId = repository.remove(userId, object);
        deleteChildrenByParentId(deletedId);
        return true;
    }

    @Override
    public abstract Boolean deleteChildrenByParentId(@Nullable final String id);

    @Override
    public abstract Boolean deleteChildrenByParentIds(@Nullable final Collection<String> ids);

    @Override
    public Boolean deleteByName(@Nullable final String name) {
        final String userId = getCurrentUserId();
        if (userId == null || name == null || name.isEmpty()) return false;
        final Collection<String> deletedIds = repository.removeByName(userId, name);
        deleteChildrenByParentIds(deletedIds);
        return true;
    }

    @Override
    public Boolean deleteByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return false;
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public Boolean deleteAll() {
        final String userId = getCurrentUserId();
        if (userId == null) return false;
        final Collection<String> deletedIds = repository.removeAll(userId);
        deleteChildrenByParentIds(deletedIds);
        return true;
    }

}
