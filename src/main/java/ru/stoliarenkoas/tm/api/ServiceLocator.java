package ru.stoliarenkoas.tm.api;

import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Map;

public interface ServiceLocator {

    Service<User> getUserService();
    Service<Project> getProjectService();
    Service<Task> getTaskService();

    User getCurrentUser();
    void setCurrentUser(final User user);

    Map<String, Command> getCommands();

    void init();

    void terminate();

}
