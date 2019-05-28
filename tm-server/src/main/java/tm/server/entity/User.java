package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.entity.Entity;
import tm.server.utils.CypherUtil;
import tm.server.utils.InputHelper;

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

    @NotNull private static final long serialVersionUID = 12345678904L;

    public enum Role {
        USER("user"),
        ADMIN("administrator");

        @Getter
        private final String displayName;

        Role(@NotNull final String displayName) {
            this.displayName = displayName;
        }
    }

    @XmlElement @NotNull private String id = UUID.randomUUID().toString();
    @XmlElement @NotNull private String login = "init";
    @XmlElement @NotNull private String passwordHash = "init";
    @XmlElement @NotNull private Role role = Role.USER;

    public User(@NotNull final String login, @NotNull final String password) {
        this(login, password, Role.USER);
    }

    public User(@NotNull final String login, @NotNull final String password, @NotNull final Role role) {
        this.login = login;
        this.passwordHash = CypherUtil.getMd5(password);
        this.role = role;
    }

    @Override @Nullable
    public String getName() {
        return login;
    }

    @Override @NotNull
    public String toString() {
        return String.format("User: %s, id: %s, Role: %s", login, id, role.displayName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        return this.id.equals(((User)obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
