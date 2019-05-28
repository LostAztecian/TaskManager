package ru.stoliarenkoas.tm.comparator;

import org.jetbrains.annotations.Nullable;
import ru.stoliarenkoas.tm.api.entity.PlannedEntity;

import java.util.Comparator;

public class StatusComparator implements Comparator<PlannedEntity> {

    @Override
    public int compare(@Nullable final PlannedEntity o1, @Nullable final PlannedEntity o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        return o1.getStatus().ordinal() - o2.getStatus().ordinal();
    }
}
