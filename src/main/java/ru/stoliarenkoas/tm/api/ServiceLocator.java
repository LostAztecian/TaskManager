package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Map;

public interface ServiceLocator {

    @NotNull Service<User> getUserService();
    @NotNull Service<Project> getProjectService();
    @NotNull Service<Task> getTaskService();

    @Nullable User getCurrentUser();
    void setCurrentUser(final @Nullable User user);

    @NotNull Map<String, Command> getCommands();

    void init(final @Nullable Class[] classes);

    void terminate();

}
