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
    private Project project;
    private String name;
    private String description;
    private Date startDate;

    @Override
    public String toString() {
        return String.format("Task: %s (%s) for project %s.", name, description, project.getName());
    }

}
