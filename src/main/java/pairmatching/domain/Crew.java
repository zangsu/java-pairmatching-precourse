package pairmatching.domain;

import pairmatching.domain.menu.Course;

public class Crew {
    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public boolean isCourseOf(Course course){
        return this.course == course;
    }
}