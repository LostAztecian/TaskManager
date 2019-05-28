package tm.client.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.client.entity.Project;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectRepository extends AbstractRepository<Project> implements tm.client.api.repository.ProjectRepository {

    @Override @NotNull
    public Collection<Project> search(@NotNull final String userId, @Nullable final String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }

}
