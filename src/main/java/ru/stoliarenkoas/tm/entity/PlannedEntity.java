package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public abstract class PlannedEntity implements Entity {

    @NotNull protected final String id = UUID.randomUUID().toString();
    @NotNull protected final String parentId;
    @NotNull protected Date creationDate = new Date();
    @Nullable protected String name;
    @Nullable protected String description;
    @Nullable protected Date startDate;
    @Nullable protected Date endDate;
    @NotNull protected Task.Status status = Task.Status.PLANNED;

    public PlannedEntity(final @NotNull String parentId) {
        this.parentId = parentId;
    }

    public enum Status {
        PLANNED("planned"),
        IN_PROGRESS("in-progress"),
        COMPLETE("complete");

        @NotNull
        public final String displayName;

        Status(final @NotNull String displayName) {
            this.displayName = displayName;
        }
    }

    @Override
    public @NotNull String getId() {
        return id;
    }

    @Override
    public @Nullable String getName() {
        return name;
    }

    @Override
    public @Nullable String getParentId() {
        return parentId;
    }

}