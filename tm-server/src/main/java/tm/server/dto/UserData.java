package tm.server.dto;

import lombok.Getter;
import lombok.Setter;
import tm.server.entity.Project;
import tm.server.entity.Task;
import tm.server.entity.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserData {

    @XmlElement private User user;
    @XmlElement private List<Project> projects;
    @XmlElement private List<Task> tasks;

}
