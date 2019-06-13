package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Project extends AbstractPlannedEntity {

    @NotNull @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL, mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
    
    @NotNull @ManyToOne(targetEntity = User.class)
    private User user;

    @Override
    public @Nullable String getUserId() {
        return user.getId();
    }
}
