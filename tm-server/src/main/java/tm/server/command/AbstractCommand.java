package tm.server.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tm.server.api.ServiceLocator;

@Getter
public abstract class AbstractCommand implements tm.server.api.Command {

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
