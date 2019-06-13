package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task extends AbstractPlannedEntity {
    
    @ManyToOne
    private Project project;

    @Override
    public @Nullable String getUserId() {
        return project.getUserId();
    }
}
