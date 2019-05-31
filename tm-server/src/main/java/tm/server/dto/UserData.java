package tm.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;
import org.jetbrains.annotations.Nullable;
import tm.common.entity.Project;
import tm.common.entity.Task;
import tm.common.entity.User;

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
    private User user;

    @Nullable
    @XmlElement(name = "project") @JsonProperty
    @XmlElementWrapper(name = "projects", nillable = true)
    private List<Project> projects;

    @Nullable
    @XmlElement(name = "task") @JsonProperty
    @XmlElementWrapper(name = "tasks", nillable = true)
    private List<Task> tasks;

}