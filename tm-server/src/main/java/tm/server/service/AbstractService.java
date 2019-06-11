package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Session;
import tm.server.api.ServiceLocator;
import tm.common.api.entity.Entity;
import tm.server.api.repository.Repository;
import tm.server.api.service.Service;
import tm.server.utils.SessionUtil;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    @NotNull
    protected final Repository<T> repository;
    @NotNull
    final ServiceLocator serviceLocator;
    @NotNull
    final Connection connection;

    AbstractService(@NotNull final Repository<T> repository,
                    @NotNull final ServiceLocator serviceLocator) {
        this.repository = repository;
        this.serviceLocator = serviceLocator;
        if (serviceLocator.getDatabaseConnection() == null) throw new NullPointerException("no database connection");
        this.connection = serviceLocator.getDatabaseConnection();
    }

    @Nullable
    protected String getCurrentUserId(@Nullable final Session session) throws Exception {
        if (session == null || !SessionUtil.isValid(session)) return null;
        if (!serviceLocator.getSessionService().isOpen(session.getId())) return null;
        return session.getUserId();
    }

    @Override @NotNull
    public Collection<T> getAll(@Nullable final Session session) throws Exception {
        final String userId = getCurrentUserId(session);
        if (userId == null) return Collections.emptySet();
        return repository.findAll(userId);
    }

    @Override @NotNull
    public Collection<T> getAllByName(@Nullable final Session session, @Nullable final String name) throws Exception {
        final String userId = getCurrentUserId(session);
        if (name == null || name.isEmpty() || userId == null) return Collections.emptySet();
        return repository.findByName(userId, name);
    }

    @Override @Nullable
    public T get(@Nullable final Session session, @Nullable final String id) throws Exception {
        if (id == null || id.isEmpty()) return null;
        final String userId = getCurrentUserId(session);
        if (userId == null) return null;
        return repository.findOne(userId, id);
    }

    @Override
    public Boolean save(@Nullable final Session session, @Nullable final T object) throws Exception {
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null) return false;
            if (object == null || object.getId().isEmpty()) return false;
            repository.merge(userId, object);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Boolean delete(@Nullable final Session session, @Nullable final String id) throws Exception {
        if (id == null || id.isEmpty()) return false;
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null) return false;
            final String deletedId = repository.remove(userId, id);
            deleteChildrenByParentId(session, deletedId);
            connection.commit();
            return deletedId != null;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Boolean delete(@Nullable final Session session, @Nullable final T object) throws Exception {
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null || object == null) return false;
            final String deletedId = repository.remove(userId, object);
            deleteChildrenByParentId(session, deletedId);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    protected abstract Boolean deleteChildrenByParentId(@Nullable final Session session, @Nullable final String id) throws Exception;

    protected abstract Boolean deleteChildrenByParentIds(@Nullable final Session session, @Nullable final Collection<String> ids) throws Exception;

    @Override
    public Boolean deleteByName(@Nullable final Session session, @Nullable final String name) throws Exception {
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null || name == null || name.isEmpty()) return false;
            final Collection<String> deletedIds = repository.removeByName(userId, name);
            deleteChildrenByParentIds(session, deletedIds);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Boolean deleteByIds(@Nullable final Session session, @Nullable final Collection<String> ids) throws Exception {
        if (ids == null || ids.isEmpty()) return false;
        final String userId = getCurrentUserId(session);
        if (userId == null) return false;
        for (String id : ids) {
            this.delete(session, id);
        }
        return true;
    }

    @Override
    public Boolean deleteAll(@Nullable final Session session) throws Exception {
        try {
            final String userId = getCurrentUserId(session);
            if (userId == null) return false;
            final Collection<String> deletedIds = repository.removeAll(userId);
            deleteChildrenByParentIds(session, deletedIds);
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

}
