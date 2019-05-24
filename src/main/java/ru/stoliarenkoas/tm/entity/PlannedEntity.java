package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PlannedEntity implements Entity, Serializable {

    private static final long serialVersionUID = 12345678901L;

    @XmlElement @NotNull protected String id = UUID.randomUUID().toString();
    @XmlElement @NotNull protected String userId = "initId";
    @XmlElement @NotNull protected Date creationDate = new Date();
    @XmlElement @Nullable protected String name;
    @XmlElement @Nullable protected String description;
    @XmlElement @Nullable protected Date startDate;
    @XmlElement @Nullable protected Date endDate;
    @XmlElement @NotNull protected Task.Status status = Task.Status.PLANNED;

    public PlannedEntity(final @NotNull String userId) {
        this.userId = userId;
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
    public @NotNull String getUserId() {
        return userId;
    }

}
