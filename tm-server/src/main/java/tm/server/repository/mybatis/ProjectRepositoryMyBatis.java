package tm.server.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.server.api.repository.ProjectRepository;
import tm.server.repository.mybatis.mapper.ProjectMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectRepositoryMyBatis implements ProjectRepository {

    private final ProjectMapper mapper;

    public ProjectRepositoryMyBatis(@NotNull final SqlSession sqlSession) {
        mapper = sqlSession.getMapper(ProjectMapper.class);
    }

    @Override
    public @NotNull Collection<Project> findAll(@NotNull String userId) throws Exception {
        return mapper.findByUserId(userId);
    }

    @Override
    public @NotNull Collection<Project> findAllAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType) throws Exception {
        final String sortColumn;
        switch (comparatorType) {
            case BY_STATUS: {
                sortColumn = "status";
                break;
            }
            case BY_START_DATE: {
                sortColumn = "startDate";
                break;
            }
            case BY_END_DATE: {
                sortColumn = "endDate";
                break;
            }
            default: {
                sortColumn = "creationDate";
            }
        }
        return mapper.findAllAndSort(userId, sortColumn);
    }

    @Override
    public @NotNull Collection<Project> findByName(@NotNull String userId, @NotNull String name) throws Exception {
        return mapper.findByName(userId, name);
    }

    @Override
    public @NotNull Collection<Project> findByNameAndSort(@NotNull String userId, @NotNull ComparatorType comparatorType, @NotNull String name) throws Exception {
        final String sortColumn;
        switch (comparatorType) {
            case BY_STATUS: {
                sortColumn = "status";
                break;
            }
            case BY_START_DATE: {
                sortColumn = "startDate";
                break;
            }
            case BY_END_DATE: {
                sortColumn = "endDate";
                break;
            }
            default: {
                sortColumn = "creationDate";
            }
        }
        return mapper.findByNameAndSort(userId, name, sortColumn);
    }

    @Nullable
    @Override
    public Project findOne(@NotNull String userId, @NotNull String id) throws Exception {
        return mapper.findOne(userId, id);
    }

    @Override
    public @NotNull Collection<Project> search(@NotNull String userId, @NotNull String searchLine) throws Exception {
        return mapper.search(userId, searchLine);
    }

    @Override
    public @NotNull Boolean persist(@NotNull Project project) throws Exception {
        mapper.persist(project);
        return true;
    }

    @Override
    public @NotNull Boolean merge(@NotNull String userId, @NotNull Project project) throws Exception {
        final Project oldProject = mapper.findOne(userId, project.getId());
        if (oldProject != null) mapper.removeById(userId, project.getId());
        mapper.persist(project);
        return true;
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull String id) throws Exception {
        final Project deletedProject = mapper.findOne(userId, id);
        if (deletedProject == null) return null;
        mapper.removeById(userId, deletedProject.getId());
        return deletedProject.getId();
    }

    @Override
    public @Nullable String remove(@NotNull String userId, @NotNull Project project) throws Exception {
        return remove(userId, project.getId());
    }

    @Override
    public @NotNull Collection<String> removeByName(@NotNull String userId, @NotNull String name) throws Exception {
        final List<Project> projects = mapper.findByName(userId, name);
        mapper.removeByName(userId, name);
        return projects.stream().map(Project::getId).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> removeAll(@NotNull String userId) throws Exception {
        final List<Project> projects = mapper.findByUserId(userId);
        mapper.clear(userId);
        return projects.stream().map(Project::getId).collect(Collectors.toSet());
    }

}
