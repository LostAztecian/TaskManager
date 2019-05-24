package ru.stoliarenkoas.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Entity {

    @NotNull String getId();
    @Nullable String getName();
    @Nullable String getUserId();

}
