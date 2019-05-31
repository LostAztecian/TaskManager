package tm.server.command.general;

import org.jetbrains.annotations.NotNull;
import tm.common.comparator.ComparatorType;
import tm.server.command.AbstractCommand;
import tm.server.utils.InputHelper;

import java.io.IOException;

public class SetSortMethodCommand extends AbstractCommand {

    @NotNull public static final String NAME = "set-sort-method";
    @NotNull private static final String DESCRIPTION = "chose sort method for result output";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public void run() throws IOException {
        System.out.println("[SELECT SORT METHOD]");
        System.out.println("OPTIONS:");
        for (ComparatorType comparatorType : ComparatorType.values()) {
            System.out.println(comparatorType.commandName);
        }
        final String input = InputHelper.requestLine("ENTER SORT TYPE:", true);
        ComparatorType comparatorType = null;
        for (ComparatorType ct : ComparatorType.values()) {
            if (ct.commandName.equals(input)) {
                comparatorType = ct;
                break;
            }
        }
        if (comparatorType == null) {
            System.out.println("[INAPPROPRIATE METHOD]");
            getServiceLocator().setCurrentSortMethod(ComparatorType.BY_CREATION_DATE.comparator);
            System.out.println("METHOD SET TO CREATION DATE");
            System.out.println();
            return;
        }

        getServiceLocator().getServerService().setSortMethod(comparatorType);
        System.out.println("[SORT METHOD UPDATED]");
    }
}
