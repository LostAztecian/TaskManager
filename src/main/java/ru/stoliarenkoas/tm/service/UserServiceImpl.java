package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.entity.User;

import java.util.Collection;

public class UserServiceImpl extends AbstractService<User> {

    private final ProjectServiceImpl projectService;

    public UserServiceImpl(final Repository<User> repository, final ProjectServiceImpl projectService) {
        super(repository);
        this.projectService = projectService;
    }

    @Override
    public void save(final User user) {
        if (!isValid(user)) return;
        super.save(user);
    }

    private boolean isValid(final User user) {
        if (user == null) return false;
        if (user.getLogin() == null || user.getLogin().isEmpty()) return false;
        if (user.getPwdHash() == null || user.getPwdHash().isEmpty()) return false;
        if (user.getRole() == null) return false;
        return true;
    }

    @Override
    public void delete(final String userId) {
        if (userId == null || userId.isEmpty()) return;
        final Collection<String> projectIds = repository.findOne(userId).getProjectIds();
        projectService.deleteByIds(projectIds);
        repository.remove(userId);
    }

}
