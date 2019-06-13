package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.ProjectDTO;
import tm.server.api.repository.ProjectRepository;
import tm.server.repository.mybatis.mapper.ProjectMapper;
import tm.server.utils.DatabaseUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectRepositoryMyBatis implements ProjectRepository {

    private final ProjectMapper mapper;

    public ProjectRepositoryMyBatis(@NotNull final SqlSession sqlSession) {
        mapper = sqlSession.getMapper(ProjectMapper.class);
    }

    @Override
    public @NotNull Collection<ProjectDTO> findAll(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @NotNull Collection<ProjectDTO> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        final String sortColumn = DatabaseUtil.getSortColumn(comparatorType);
        return mapper.findAllAndSort(userId, sortColumn);
    }

    @Override
    public @NotNull Collection<ProjectDTO> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return mapper.findByName(userId, name);
    }

    @Override
    public @NotNull Collection<ProjectDTO> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        final String sortColumn = DatabaseUtil.getSortColumn(comparatorType);
        return mapper.findByNameAndSort(userId, name, sortColumn);
    }

    @Nullable
    @Override
    public ProjectDTO findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return mapper.findOne(userId, id);
    }

    @Override
    public @NotNull Collection<ProjectDTO> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return mapper.search(userId, searchLine);
    }

    @Override
    public @NotNull Boolean persist(@NotNull ProjectDTO project) throws Exception {
        mapper.persist(project);
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull ProjectDTO project) throws Exception {
        final ProjectDTO oldProject = mapper.findOne(userId, project.getId());
        if (oldProject != null) mapper.removeById(userId, project.getId());
        mapper.persist(project);
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final ProjectDTO deletedProject = mapper.findOne(userId, id);
        if (deletedProject == null) return null;
        mapper.removeById(userId, deletedProject.getId());
        return deletedProject.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull ProjectDTO project) throws Exception {
        return remove(userId, project.getId());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final List<ProjectDTO> projects = mapper.findByName(userId, name);
        mapper.removeByName(userId, name);
        return projects.stream().map(ProjectDTO::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final List<ProjectDTO> projects = mapper.findByUserId(userId);
        mapper.clear(userId);
        return projects.stream().map(ProjectDTO::getId).collect(Collectors.toSet());
    }

}
