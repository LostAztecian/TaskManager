package tm.common.api;

import org.jetbrains.annotations.NotNull;

public interface Command {

    @NotNull
    String getName();

    @NotNull
    String getDescription();

    boolean isPrivate();

    void execute() throws Exception;

}
