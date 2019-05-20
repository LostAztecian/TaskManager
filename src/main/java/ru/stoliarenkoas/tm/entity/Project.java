package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import ru.stoliarenkoas.tm.api.Entity;

import java.util.UUID;

@Getter
@Setter
public class Project implements Entity {

    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private final String userId;

    public Project(String parentId) {
        this.userId = parentId;
    }

    public String getParentId() {
        return userId;
    }

    @Override
    public String toString() {
        return String.format("Project: %s (%s) belongs to userId:=%s.", name, description, userId);
    }

}
