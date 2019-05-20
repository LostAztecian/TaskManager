package ru.stoliarenkoas.tm.service;

import ru.stoliarenkoas.tm.api.Repository;
import ru.stoliarenkoas.tm.entity.User;

public class UserServiceImpl extends AbstractService<User> {


    public UserServiceImpl(final Repository<User> repository, final ProjectServiceImpl projectService) {
        super(repository, projectService);
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

}
