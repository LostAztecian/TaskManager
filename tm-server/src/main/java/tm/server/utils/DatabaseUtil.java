package tm.server.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;

public class DatabaseUtil {

    @NotNull
    public static String getSortColumn(@Nullable final ComparatorType comparatorType) {
        if (comparatorType == null) return "creationDate";
        switch (comparatorType) {
            case BY_STATUS: {
                return "status";
            }
            case BY_START_DATE: {
                return "startDate";
            }
            case BY_END_DATE: {
                return "endDate";
            }
            case BY_CREATION_DATE: {
                return "creationDate";
            }
        }
        return "creationDate";
    }

}
