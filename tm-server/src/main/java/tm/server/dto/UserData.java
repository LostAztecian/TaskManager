package tm.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;
import tm.common.entity.Project;
import tm.common.entity.Task;
import tm.common.entity.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlNullPolicy(isSetPerformedForAbsentNode = false)
public class UserData {

    @XmlElement private User user;

    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects", nillable = true)
    private List<Project> projects;

    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks", nillable = true)
    private List<Task> tasks;

}
