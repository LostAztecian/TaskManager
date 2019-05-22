package ru.stoliarenkoas.tm.entity.comparator;

import ru.stoliarenkoas.tm.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Comparator;

public class StartDateComparator implements Comparator<PlannedEntity> {

    @Override
    public int compare(PlannedEntity o1, PlannedEntity o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        if (o1.getStartDate() == null && o2.getStartDate() == null) return 0;
        if (o1.getStartDate() == null || o2.getStartDate() == null) return o1.getStartDate() == null ? 1 : -1;
        return o1.getStartDate().compareTo(o2.getStartDate());
    }

}
