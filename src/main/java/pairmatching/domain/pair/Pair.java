package pairmatching.domain.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.exception.PairExceptionMaker;

public class Pair {
    public static final int MIN_CREW_SIZE = 2;
    public static final int MAX_CREW_SIZE = 3;
    private final List<String> crewNames;

    public Pair(Collection<String> crewNames) {
        validateSize(crewNames);
        this.crewNames = new ArrayList<>(crewNames);
    }

    private void validateSize(Collection<String> crews) {
        if(crews.size() < MIN_CREW_SIZE || crews.size() > MAX_CREW_SIZE) {
            throw PairExceptionMaker.INVALID_CREW_SIZE.makeException();
        }
    }
}
