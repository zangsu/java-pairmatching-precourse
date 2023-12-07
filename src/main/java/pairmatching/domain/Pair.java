package pairmatching.domain;

import java.util.List;
import pairmatching.exception.PairExceptionMaker;

public class Pair {
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        validate(crews);
        this.crews = crews;
    }

    private void validate(List<Crew> crews) {
        if(crews.size() < 2 || crews.size() > 3) {
            throw PairExceptionMaker.INVALID_CREW_SIZE.makeException();
        }
        Course course = crews.get(0).getCourse();
        if(crews.stream().anyMatch(crew -> !crew.isCourseOf(course))) {
            throw PairExceptionMaker.PAIR_CREW_COURSE_DIFFERENT.makeException();
        }
    }
}
