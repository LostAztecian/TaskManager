package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.TaskDTO;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends AbstractPlannedEntity {
    
    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {
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

    @Override @Nullable
    public String getUserId() {
        return project.getUserId();
    }

}
