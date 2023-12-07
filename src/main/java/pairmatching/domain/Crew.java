package pairmatching.domain;

public class Crew {
    //todo 해당 정보 없애도 될 것 같음
    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public boolean isCourseOf(Course course){
        return this.course == course;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}