package ru.stoliarenkoas.tm.entity.comparator;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.entity.PlannedEntity;

import java.util.Comparator;

public enum ComparatorType {
    BY_CREATION_DATE(new CreationDateComparator(), "creation-date"),
    BY_START_DATE(new StartDateComparator(), "start-date"),
    BY_END_DATE(new EndDateComparator(), "end-date"),
    BY_STATUS(new StatusComparator(), "status");

    public final @NotNull Comparator<PlannedEntity> comparator;
    public final @NotNull String commandName;

    private ComparatorType(final @NotNull Comparator<PlannedEntity> comparator, final @NotNull String commandName) {
        this.comparator = comparator;
        this.commandName = commandName;
    }
}
