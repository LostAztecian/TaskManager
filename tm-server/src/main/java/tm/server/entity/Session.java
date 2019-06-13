package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Session {

    @NotNull @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    private Date creationDate = new Date();

    @NotNull @Enumerated(EnumType.STRING)
    private ComparatorType sortMethod = ComparatorType.BY_CREATION_DATE;

    @Nullable
    private String hash;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", sortMethod=" + sortMethod +
                ", user=" + user +
                '}';
    }
}
