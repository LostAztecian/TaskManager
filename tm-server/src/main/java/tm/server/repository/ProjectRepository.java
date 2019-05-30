package tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Project;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectRepository extends AbstractRepository<Project> implements tm.server.api.repository.ProjectRepository {

    @Override @NotNull
    public Collection<Project> search(@NotNull final String userId, @Nullable final String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }

}
