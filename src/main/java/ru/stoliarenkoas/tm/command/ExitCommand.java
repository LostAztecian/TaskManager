package ru.stoliarenkoas.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.ServiceLocator;

public class ExitCommand extends AbstractCommand {

    public static final String NAME = "exit";
    private static final String DESCRIPTION = "end program";

    @NotNull
    @Override
    public String getName() { return NAME; }

    @NotNull
    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public void run() {
        getServiceLocator().terminate();
    }

}
