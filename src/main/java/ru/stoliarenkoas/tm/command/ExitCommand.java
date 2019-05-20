package ru.stoliarenkoas.tm.command;

import ru.stoliarenkoas.tm.api.ServiceLocator;

public class ExitCommand extends AbstractCommand {

    public static final String NAME = "exit";
    private static final String DESCRIPTION = "end program";

    public ExitCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, false);
    }

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public void run() {
        getServiceLocator().terminate();
    }

}
