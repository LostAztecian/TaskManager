package ru.stoliarenkoas.tm.command.general;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {

    @NotNull public static final String NAME = "exit";
    @NotNull private static final String DESCRIPTION = "end program";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
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
