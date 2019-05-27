package ru.stoliarenkoas.tm.entity;

import org.jetbrains.annotations.NotNull;

public enum Status {
    PLANNED("planned"),
    IN_PROGRESS("in-progress"),
    COMPLETE("complete");

    @NotNull
    public final String displayName;

    Status(final @NotNull String displayName) {
        this.displayName = displayName;
    }
}
