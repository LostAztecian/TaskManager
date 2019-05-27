package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface Command {

    void execute() throws Exception;

    @NotNull String getName();

    @NotNull String getDescription();

    boolean isPrivate();

}
