package tm.server.repository.map;

import org.jetbrains.annotations.NotNull;
import tm.common.entity.ProjectDTO;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectRepositoryMap extends AbstractRepositoryMap<ProjectDTO> implements tm.server.api.repository.ProjectRepository {

    @Override @NotNull
    public Collection<ProjectDTO> search(@NotNull final String userId, @NotNull final String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }

}
