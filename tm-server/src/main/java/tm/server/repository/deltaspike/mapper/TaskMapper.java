package tm.server.repository.deltaspike.mapper;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import tm.common.entity.TaskDTO;
import tm.server.entity.Project;
import tm.server.entity.Task;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

public class TaskMapper extends SimpleQueryInOutMapperBase<Task, TaskDTO> {
    
    @Inject
    private EntityManager entityManager;

    @Override
    protected Object getPrimaryKey(TaskDTO taskDTO) {
        return taskDTO.getId();
    }

    @Override
    protected TaskDTO toDto(Task task) {
        final TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setProjectId(task.getProject().getId());
        dto.setUserId(task.getProject().getUser().getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setCreationDate(task.getCreationDate());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());
        dto.setStatus(task.getStatus());
        return dto;
    }

    @Override
    protected Task toEntity(Task task, TaskDTO taskDTO) {
        final Project project = entityManager.find(Project.class, taskDTO.getProjectId());
        task.setProject(project);
        task.setId(taskDTO.getId());
        task.setName(Optional.ofNullable(taskDTO.getName()).orElse("NNM"));
        task.setDescription(Optional.ofNullable(taskDTO.getDescription()).orElse("NO DESCRIPTION"));
        task.setCreationDate(taskDTO.getCreationDate());
        task.setStartDate(Optional.ofNullable(taskDTO.getStartDate()).orElse(task.getCreationDate()));
        task.setEndDate(Optional.ofNullable(taskDTO.getEndDate()).orElse(task.getCreationDate()));
        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
