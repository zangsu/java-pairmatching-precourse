package pairmatching.domain.pair;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.exception.PairExceptionMaker;

public class Pair {
    public static final int MIN_CREW_SIZE = 2;
    public static final int MAX_CREW_SIZE = 3;
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        validate(crews);
        this.crews = crews;
    }

    private void validate(List<Crew> crews) {
        validateSize(crews);
        validateAllSameCourse(crews);
    }

    private void validateAllSameCourse(List<Crew> crews) {
        Course course = crews.get(0).getCourse();
        if(crews.stream().anyMatch(crew -> !crew.isCourseOf(course))) {
            throw PairExceptionMaker.PAIR_CREW_COURSE_DIFFERENT.makeException();
        }
    }

    private void validateSize(List<Crew> crews) {
        if(crews.size() < MIN_CREW_SIZE || crews.size() > MAX_CREW_SIZE) {
            throw PairExceptionMaker.INVALID_CREW_SIZE.makeException();
        }
    }
}
