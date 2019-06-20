package tm.server.repository.deltaspike.mapper;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import tm.common.entity.ProjectDTO;
import tm.server.entity.Project;
import tm.server.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

public class ProjectMapper extends SimpleQueryInOutMapperBase<Project, ProjectDTO> {

    @Inject
    private EntityManager entityManager;

    @Override
    protected Object getPrimaryKey(ProjectDTO projectDTO) {
        return projectDTO.getId();
    }

    @Override
    protected ProjectDTO toDto(Project project) {
        final ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setUserId(project.getUser().getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStatus(project.getStatus());
        dto.setCreationDate(project.getCreationDate());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        return dto;
    }

    @Override
    protected Project toEntity(Project project, ProjectDTO projectDTO) {
        project.setUser(entityManager.find(User.class, projectDTO.getUserId()));
        project.setId(projectDTO.getId());
        project.setName(Optional.ofNullable(projectDTO.getName()).orElse("NNM"));
        project.setDescription(Optional.ofNullable(projectDTO.getDescription()).orElse("NO DESCRIPTION"));
        project.setStatus(projectDTO.getStatus());
        project.setCreationDate(projectDTO.getCreationDate());
        project.setStartDate(Optional.ofNullable(projectDTO.getStartDate()).orElse(project.getCreationDate()));
        project.setEndDate(Optional.ofNullable(projectDTO.getEndDate()).orElse(project.getCreationDate()));
        return project;
    }

}
