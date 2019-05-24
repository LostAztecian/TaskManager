package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Project extends PlannedEntity implements Serializable {

    private static final long serialVersionUID = 12345678902L;

    public Project(final @NotNull String userId) {
        super(userId);
    }

    @Override @NotNull
    public String getUserId() {
        return userId;
    }

    @Override @NotNull
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("Project: %s (%s) belongs to userId:=%s.%n"+
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
        if (!(obj instanceof Project)) return false;
        return this.id.equals(((Project)obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
