package tm.server.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tm.common.api.Command;
import tm.server.api.ServiceLocator;

@Getter
public abstract class AbstractCommand implements Command {

    @NotNull @Setter
    private ServiceLocator serviceLocator;

    @Override
    public final void execute() throws Throwable {
        if (isPrivate() && serviceLocator.getCurrentSession() == null) {
            System.out.println("COMMAND IS NOT AVAILABLE WITHOUT AUTHORIZATION");
            System.out.println();
            return;
        }
        run();
    }

    protected abstract void run() throws Throwable;

    @Override
    public abstract boolean isPrivate();


    @Override
    public String toString() {
        return String.format("%s: %s.", getName(), getDescription());
    }

}
