package tm.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.ProjectDTO;
import tm.common.entity.TaskDTO;
import tm.common.entity.UserDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlNullPolicy(isSetPerformedForAbsentNode = false)
public class UserData {

    @Nullable
    @XmlElement @JsonProperty
    private UserDTO user;

    @Nullable
    @XmlElement(name = "project") @JsonProperty
    @XmlElementWrapper(name = "projects", nillable = true)
    private List<ProjectDTO> projects;

    @Nullable
    @XmlElement(name = "task") @JsonProperty
    @XmlElementWrapper(name = "tasks", nillable = true)
    private List<TaskDTO> tasks;

}
