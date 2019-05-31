package tm.common.comparator;

import org.jetbrains.annotations.Nullable;
import tm.common.api.entity.PlannedEntity;

import java.util.Comparator;

public class CreationDateComparator implements Comparator<PlannedEntity> {

    @Override
    public int compare(@Nullable final PlannedEntity o1, @Nullable final PlannedEntity o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }

}