package ru.stoliarenkoas.tm.entity.comparator;

import ru.stoliarenkoas.tm.entity.PlannedEntity;
import ru.stoliarenkoas.tm.entity.Task;

import java.util.Comparator;

public class CreationDateComparator implements Comparator<PlannedEntity> {

    @Override
    public int compare(PlannedEntity o1, PlannedEntity o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }

}
