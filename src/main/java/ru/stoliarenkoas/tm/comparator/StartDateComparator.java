package ru.stoliarenkoas.tm.comparator;

import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;

import java.util.Comparator;

public class StartDateComparator implements Comparator<PlannedEntity> {

    @Override
    public int compare(@Nullable final PlannedEntity o1, @Nullable final PlannedEntity o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        if (o1.getStartDate() == null && o2.getStartDate() == null) return 0;
        if (o1.getStartDate() == null || o2.getStartDate() == null) return o1.getStartDate() == null ? 1 : -1;
        return o1.getStartDate().compareTo(o2.getStartDate());
    }

}