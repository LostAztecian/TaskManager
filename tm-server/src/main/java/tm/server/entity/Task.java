package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.TaskDTO;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task extends AbstractPlannedEntity {
    
    @ManyToOne
    private Project project;

    @Override @Nullable
    public String getUserId() {
        return project.getUserId();
    }

    public Task(@NotNull final TaskDTO dto, @NotNull final Project project) {
        this.project = project;
        setId(dto.getId());
        setName(dto.getName() == null ? "NNM" : dto.getName());
        setDescription(dto.getDescription() == null ? "no description" : dto.getDescription());
        setStatus(dto.getStatus());
        setCreationDate(dto.getCreationDate());
        setStartDate(dto.getStartDate() == null ? dto.getCreationDate() : dto.getStartDate());
        setEndDate(dto.getEndDate() == null ? dto.getCreationDate() : dto.getEndDate());
    }

    public TaskDTO toDTO() {
        final TaskDTO dto = new TaskDTO();
        dto.setId(this.getId());
        dto.setProjectId(this.project.getId());
        dto.setUserId(this.project.getUser().getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        dto.setStatus(this.getStatus());
        dto.setCreationDate(this.getCreationDate());
        dto.setStartDate(this.getStartDate());
        dto.setEndDate(this.getEndDate());
        return dto;
    }
}
