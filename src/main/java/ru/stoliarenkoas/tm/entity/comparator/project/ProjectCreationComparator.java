package ru.stoliarenkoas.tm.entity.comparator.project;

import ru.stoliarenkoas.tm.entity.Project;

import java.util.Comparator;

public class ProjectCreationComparator implements Comparator<Project> {

    @Override
    public int compare(Project o1, Project o2) {
        if (o1 == null || o2 == null) throw new NullPointerException("null comparison");
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
    
}
