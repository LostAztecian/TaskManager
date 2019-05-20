package ru.stoliarenkoas.tm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stoliarenkoas.tm.api.Entity;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Task implements Entity {

    private final String id = UUID.randomUUID().toString();
    private final String projectId;
    private String name;
    private String description;
    private Date startDate;

    public String getUserId() {
        return projectId;
    }

    @Override
    public String getParentId() {
        return projectId;
    }

    @Override
    public String toString() {
        return String.format("Task: %s (%s) for project id:=%s.", name, description, projectId);
    }

}
