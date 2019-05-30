package tm.common.api;

import org.jetbrains.annotations.NotNull;

public interface Command {

    void execute() throws Exception;

    @NotNull
    String getName();

    @NotNull
    String getDescription();

    boolean isPrivate();

}
