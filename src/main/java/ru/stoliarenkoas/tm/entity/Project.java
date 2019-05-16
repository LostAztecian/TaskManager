package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Project {

    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private final Set<String> taskIds = new LinkedHashSet<>();

    @Override
    public String toString() {
        return String.format("Project: %s (%s).", name, description);
    }

}
