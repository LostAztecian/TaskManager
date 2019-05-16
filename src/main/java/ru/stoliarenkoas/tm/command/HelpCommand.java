package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;

public final class HelpCommand extends Command {

    public static final String NAME = "help";
    private static final String DESCRIPTION = "show all commands";

    public HelpCommand(final Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void execute() {
        getBootstrap().getCommands().values().forEach(System.out::println);
    }

}
