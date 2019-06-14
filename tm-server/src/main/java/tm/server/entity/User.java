package tm.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.UserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class User implements tm.common.api.entity.Entity {

    public User(@NotNull final UserDTO userDTO) {
        this.id = userDTO.getId();
        this.login = userDTO.getLogin();
        this.passwordHash = userDTO.getPasswordHash();
        this.role = userDTO.getRole();
    }

    @NotNull @Id
    private String id = UUID.randomUUID().toString();

    @Nullable @Column(unique = true)
    private String login;

    @Nullable @Column(name = "pwdHash")
    private String passwordHash;

    @NotNull @Enumerated(EnumType.STRING)
    private UserDTO.Role role = UserDTO.Role.USER;

    @NotNull @OneToMany(targetEntity = Project.class, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Project> projects = new ArrayList<>();

    @NotNull @OneToMany(targetEntity = Session.class, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Session> sessions = new ArrayList<>();

    @Override
    public @Nullable String getName() {
        return login;
    }

    public UserDTO toDTO() {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setLogin(login);
        userDTO.setPasswordHash(passwordHash);
        userDTO.setRole(role);
        return userDTO;
    }

}
