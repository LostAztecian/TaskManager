package ru.stoliarenkoas.tm.command.general;

import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.ServiceLocator;
import ru.stoliarenkoas.tm.command.AbstractCommand;

public final class HelpCommand extends AbstractCommand {

    @NotNull public static final String NAME = "help";
    @NotNull private static final String DESCRIPTION = "show all commands";

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
        final boolean loggedIn = getServiceLocator().getCurrentUser() != null;
        getServiceLocator().getCommands().values().forEach(c -> {if (!c.isPrivate() || loggedIn) System.out.println(c);});
    }

}
