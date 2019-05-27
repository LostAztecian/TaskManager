package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Project implements PlannedEntity, Serializable {

    private static final long serialVersionUID = 12345678902L;
    @NotNull private String id = UUID.randomUUID().toString();
    @NotNull private String userId = "initId";
    @NotNull private Status status = Status.PLANNED;
    @Nullable private String name;
    @Nullable private String description;
    @NotNull private Date creationDate = new Date();
    @Nullable private Date startDate;
    @Nullable private Date endDate;

    public Project(final @NotNull String userId) {
        this.userId = userId;
    }


    @NotNull public String getUserId() {
        return userId;
    }

    @Override @NotNull
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("Project: %s (%s) belongs to id:=%s.%n"+
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
