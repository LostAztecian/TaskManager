package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.ProjectDTO;

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
    
    @NotNull @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    private User user;

    @Override
    public @Nullable String getUserId() {
        return user.getId();
    }

    public Project(@NotNull final ProjectDTO dto, @NotNull final User user) {
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setDescription(dto.getDescription());
        this.setStatus(dto.getStatus());
        this.setCreationDate(dto.getCreationDate());
        this.setStartDate(dto.getStartDate());
        this.setEndDate(dto.getEndDate());
        this.user = user;
    }

    public ProjectDTO toDTO() {
        final ProjectDTO dto = new ProjectDTO();
        dto.setId(this.getId());
        dto.setUserId(this.user.getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        dto.setStatus(this.getStatus());
        dto.setCreationDate(this.getCreationDate());
        dto.setStartDate(this.getStartDate());
        dto.setEndDate(this.getEndDate());
        return dto;
    }

}
