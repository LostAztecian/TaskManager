package ru.stoliarenkoas.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.Entity;
import ru.stoliarenkoas.tm.console.InputHelper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class User implements Entity, Serializable {

    private static final long serialVersionUID = 12345678904L;

    public enum Role {
        USER("user"),
        ADMIN("administrator");

        @Getter
        private final String displayName;

        Role(final @NotNull String displayName) {
            this.displayName = displayName;
        }
    }

    @XmlElement @NotNull private String userId = UUID.randomUUID().toString();
    @XmlElement @NotNull private String login = "init";
    @XmlElement @NotNull private String pwdHash = "init";
    @XmlElement @NotNull private Role role = Role.USER;

    public User(final @NotNull String login, final @NotNull String password) {
        this(login, password, Role.USER);
    }

    public User(final @NotNull String login, final @NotNull String password, final @NotNull Role role) {
        this.login = login;
        this.pwdHash = InputHelper.getMd5(password);
        this.role = role;
    }

    @Override @NotNull @JsonIgnore
    public String getUserId() {
        return userId;
    }

    @Override @NotNull @JsonIgnore
    public String getId() {
        return userId;
    }

    @Override @Nullable @JsonIgnore
    public String getName() {
        return login;
    }

    @Override @NotNull
    public String toString() {
        return String.format("User: %s, id: %s, Role: %s", login, userId, role.displayName);
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
