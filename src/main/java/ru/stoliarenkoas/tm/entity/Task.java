package ru.stoliarenkoas.tm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Task implements Entity {

    @NotNull private final String id = UUID.randomUUID().toString();
    @Nullable private final String projectId;
    @Nullable private String name;
    @Nullable private String description;
    @Nullable private Date startDate;

    @Nullable public String getUserId() {
        return projectId;
    }

    @Override
    public String getParentId() {
        return projectId;
    }

    @Override
    public String toString() {
        return String.format("Task: %s (%s) for project id:=%s.", name, description, projectId);
    }

}
