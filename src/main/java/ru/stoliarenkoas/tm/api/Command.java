package ru.stoliarenkoas.tm.api;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;

    String getName();

    String getDescription();

    boolean isPrivate();
}
