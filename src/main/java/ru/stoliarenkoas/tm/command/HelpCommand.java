package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;

public final class HelpCommand extends Command {

    public static final String NAME = "help";
    private static final String DESCRIPTION = "show all commands";

    public HelpCommand(final Bootstrap bootstrap) {
        super(bootstrap, false);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() {
        final boolean loggedIn = getBootstrap().getCurrentUser() != null;
        getBootstrap().getCommands().values().forEach(c -> {if (!c.isPrivate() || loggedIn) System.out.println(c);});
    }

}
