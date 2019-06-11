package tm.server.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Project extends PlannedEntity {

    @NotNull @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

}
