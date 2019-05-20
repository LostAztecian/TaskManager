package ru.stoliarenkoas.tm.command;

import com.jcabi.manifests.Manifests;
import ru.stoliarenkoas.tm.api.ServiceLocator;

import java.io.IOException;

public class AboutCommand extends AbstractCommand {

    public static final String NAME = "about";
    private static final String DESCRIPTION = "show build information";

    public AboutCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator, false);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    protected void run() throws IOException {
        System.out.println("Build version: " + Manifests.read("JCabi-Version"));
        System.out.println();
    }

}
