package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

        Role(final @NotNull String displayName) {
            this.displayName = displayName;
        }
    }

    @NotNull private final String userId = UUID.randomUUID().toString();
    @NotNull private String login;
    @NotNull private String pwdHash;
    @NotNull private Role role;

    public User(final @NotNull String login, final @NotNull String password) {
        this(login, password, Role.USER);
    }

    public User(final @NotNull String login, final @NotNull String password, final @NotNull Role role) {
        this.login = login;
        this.pwdHash = InputHelper.getMd5(password);
        this.role = role;
    }

    @Override @Nullable
    public String getParentId() {
        return null;
    }

    @Override @NotNull
    public String getId() {
        return userId;
    }

    @Override @Nullable
    public String getName() {
        return login;
    }

    @Override @NotNull
    public String toString() {
        return "User: " + login + "status: " + role.displayName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        return this.userId.equals(((User)obj).userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
