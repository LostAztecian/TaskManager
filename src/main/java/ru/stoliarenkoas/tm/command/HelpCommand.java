package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;

public final class HelpCommand extends AbstractCommand {

    public static final String NAME = "help";
    private static final String DESCRIPTION = "show all commands";

    public HelpCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, false);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() {
        final boolean loggedIn = getServiceLocator().getCurrentUser() != null;
        getServiceLocator().getCommands().values().forEach(c -> {if (!c.isPrivate() || loggedIn) System.out.println(c);});
    }

}
