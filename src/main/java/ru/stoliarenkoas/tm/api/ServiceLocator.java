package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;
import ru.stoliarenkoas.tm.api.service.ProjectService;
import ru.stoliarenkoas.tm.api.service.TaskService;
import ru.stoliarenkoas.tm.api.service.UserService;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Comparator;
import java.util.Map;

public interface ServiceLocator {

    @NotNull UserService getUserService();
    @NotNull ProjectService getProjectService();
    @NotNull TaskService getTaskService();

    @Nullable User getCurrentUser();
    void setCurrentUser(final @Nullable User user);

    @NotNull Comparator<PlannedEntity> getCurrentSortMethod();
    void setCurrentSortMethod(final @NotNull Comparator<PlannedEntity> comparator);

    @NotNull Map<String, Command> getCommands();

    void init(final @Nullable Class[] classes);

    void terminate();

}
