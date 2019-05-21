package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;

import java.util.UUID;

@Getter
@Setter
public class Project implements Entity {

    @NotNull private final String id = UUID.randomUUID().toString();
    @NotNull private final String userId;
    @Nullable private String name;
    @Nullable private String description;

    public Project(final @Nullable String parentId) {
        this.userId = parentId;
    }

    @Override @NotNull
    public String getParentId() {
        return userId;
    }

    @Override @NotNull
    public String toString() {
        return String.format("Project: %s (%s) belongs to userId:=%s.", name, description, userId);
    }

}
