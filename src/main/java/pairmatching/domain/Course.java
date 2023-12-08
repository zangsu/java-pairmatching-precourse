package pairmatching.domain;

import pairmatching.exception.PairExceptionMaker;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public static Course from(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw PairExceptionMaker.INVALID_COURSE.makeException();
    }
}
