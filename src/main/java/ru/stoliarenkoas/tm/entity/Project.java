package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

@Getter
@Setter
public class Project extends PlannedEntity {


    public Project(final @NotNull String parentId) {
        super(parentId);
    }

    @Override @NotNull
    public String getParentId() {
        return parentId;
    }

    @Override @NotNull
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("Project: %s (%s) belongs to userId:=%s.%n"+
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
        if (!(obj instanceof Project)) return false;
        return this.id.equals(((Project)obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
