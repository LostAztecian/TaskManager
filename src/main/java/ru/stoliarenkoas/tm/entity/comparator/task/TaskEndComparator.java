package ru.stoliarenkoas.tm.entity.comparator.task;

import ru.stoliarenkoas.tm.entity.Task;

import java.util.Comparator;

public class TaskEndComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        if (o1.getEndDate() == null && o2.getEndDate() == null) return 0;
        if (o1.getEndDate() == null || o2.getEndDate() == null) return o1.getEndDate() == null ? 1 : -1;
        return o1.getEndDate().compareTo(o2.getEndDate());
    }
}
