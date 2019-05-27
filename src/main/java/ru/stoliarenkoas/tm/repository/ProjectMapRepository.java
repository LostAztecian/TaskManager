package ru.stoliarenkoas.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.repository.ProjectRepository;
import ru.stoliarenkoas.tm.entity.Project;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectMapRepository extends AbstractMapRepository<Project> implements ProjectRepository {

    @Override
    public @NotNull Collection<Project> search(@NotNull String userId, @Nullable String searchLine) {
        return findAll(userId).stream().filter(t ->
                Optional.ofNullable(t.getName()).orElse("").contains(searchLine) ||
                Optional.ofNullable(t.getDescription()).orElse("").contains(searchLine))
                .collect(Collectors.toSet());
    }

}
