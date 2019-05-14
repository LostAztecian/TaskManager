package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Task {

    private final String id = UUID.randomUUID().toString();
    private Project project;
    private String name;
    private String description;

}
