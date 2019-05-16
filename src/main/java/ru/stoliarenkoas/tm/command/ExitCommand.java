package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.Bootstrap;

public class ExitCommand extends Command {

    public static final String NAME = "exit";
    private static final String DESCRIPTION = "end program";

    public ExitCommand(final Bootstrap bootstrap) {
        super(bootstrap, NAME, DESCRIPTION);
    }

    @Override
    public void execute() {
        getBootstrap().terminate();
    }
}
