package ru.stoliarenkoas.tm.dto;

import lombok.Getter;
import lombok.Setter;
import ru.stoliarenkoas.tm.entity.Project;
import ru.stoliarenkoas.tm.entity.Task;
import ru.stoliarenkoas.tm.entity.User;

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
