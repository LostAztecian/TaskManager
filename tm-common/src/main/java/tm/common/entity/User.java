package tm.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.Entity;

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
    @XmlElement @Nullable private String login;
    @XmlElement @Nullable private String passwordHash;
    @XmlElement @NotNull private Role role = Role.USER;

    @Override @Nullable @JsonIgnore
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
