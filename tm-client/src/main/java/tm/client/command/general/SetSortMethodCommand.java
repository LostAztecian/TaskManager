package tm.client.command.general;

import org.jetbrains.annotations.NotNull;
import tm.client.command.AbstractCommand;
import tm.client.comparator.ComparatorType;
import tm.client.utils.InputHelper;
import tm.common.api.webservice.SessionDTO;

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
        final SessionDTO session = getServiceLocator().getCurrentSession();
        if (session == null) return;
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

        final Boolean success = getServiceLocator().getServerService().setSortMethod(session, tm.common.api.webservice.ComparatorType.valueOf(comparatorType.toString()));
        System.out.println(success ? "[SORT METHOD UPDATED]" : "[SORT METHOD UPDATE FAILURE]");
        System.out.println();
    }
}
