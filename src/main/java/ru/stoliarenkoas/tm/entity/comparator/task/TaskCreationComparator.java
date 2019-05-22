package ru.stoliarenkoas.tm.entity.comparator.task;

import ru.stoliarenkoas.tm.entity.Task;

import java.util.Comparator;

public class TaskCreationComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }

}
