package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.console.InputHelper;

import java.util.UUID;

@Getter
@Setter
public class User implements Entity {

    public enum Role {
        USER("user"),
        ADMIN("administrator");

        @Getter
        private final String displayName;

        Role(String displayName) {
            this.displayName = displayName;
        }
    }

    private final String userId = UUID.randomUUID().toString();
    private String login;
    private String pwdHash;
    private Role role;

    public User(final String login, final String password) {
        this(login, password, Role.USER);
    }

    public User(final String login, final String password, final Role role) {
        this.login = login;
        this.pwdHash = InputHelper.getMd5(password);
        this.role = role;
    }

    public String getParentId() {
        return null;
    }

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public String getName() {
        return login;
    }

    @Override
    public String toString() {
        return "User: " + login + "status: " + role.displayName;
    }
}
