package ru.stoliarenkoas.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.api.ServiceLocator;

import java.io.IOException;

@Getter
public abstract class AbstractCommand implements ru.stoliarenkoas.tm.api.Command {

    @NotNull @Setter
    private ServiceLocator serviceLocator;

    @Override
    public final void execute() throws Exception {
        if (isPrivate() && serviceLocator.getCurrentUser() == null) {
            System.out.println("COMMAND IS NOT AVAILABLE WITHOUT AUTHORIZATION");
            return;
        }
        run();
    }

    protected abstract void run() throws Exception;

    @Override
    public abstract boolean isPrivate();


    @Override
    public String toString() {
        return String.format("%s: %s.", getName(), getDescription());
    }

}
