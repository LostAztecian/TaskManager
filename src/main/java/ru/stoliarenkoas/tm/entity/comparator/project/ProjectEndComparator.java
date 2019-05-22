package ru.stoliarenkoas.tm.entity.comparator.project;

import ru.stoliarenkoas.tm.entity.Project;

import java.util.Comparator;

public class ProjectEndComparator implements Comparator<Project> {

    @Override
    public int compare(Project o1, Project o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        if (o1.getEndDate() == null && o2.getEndDate() == null) return 0;
        if (o1.getEndDate() == null || o2.getEndDate() == null) return o1.getEndDate() == null ? 1 : -1;
        return o1.getEndDate().compareTo(o2.getEndDate());
    }
}
