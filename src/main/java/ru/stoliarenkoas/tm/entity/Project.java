package ru.stoliarenkoas.tm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Project {

    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;

}
