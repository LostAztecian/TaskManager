package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Task extends PlannedEntity implements Serializable {

    private static final long serialVersionUID = 12345678903L;

    @NotNull String projectId = "initId";

    public Task(final @NotNull String userId, final @NotNull String name) {
        super(userId);
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
                userId,
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
