package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

@Getter
@Setter
public class Task extends PlannedEntity {

    public Task(final @NotNull String projectId, final @NotNull String name) {
        super(projectId);
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("Task: %s (%s) for project id:=%s.%n" +
                "Creation: %s, Start: %s, End: %s."+
                "Status: %s.",
                name,
                description,
                parentId,
                formatter.format(creationDate),
                formatter.format(startDate),
                formatter.format(endDate),
                status.displayName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        return this.id.equals(((Task)obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
