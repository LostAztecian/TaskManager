package tm.server.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @NotNull @Id
    private String id = UUID.randomUUID().toString();

    @NotNull private String login = "initLogin";

    @NotNull @Column(name = "pwdHash")
    private String passwordHash = "initPassword";

    @NotNull @Enumerated(EnumType.STRING)
    private tm.common.entity.User.Role role = tm.common.entity.User.Role.USER;

    @NotNull @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

}
