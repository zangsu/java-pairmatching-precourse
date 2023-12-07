package pairmatching.domain.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import pairmatching.exception.PairExceptionMaker;

public class Pair {
    public static final int MIN_PAIR_SIZE = 2;
    public static final int MAX_PAIR_SIZE = 3;
    private final List<String> crewNames;

    public Pair(Collection<String> crewNames) {
        validateSize(crewNames);
        this.crewNames = new ArrayList<>(crewNames);
    }

    private void validateSize(Collection<String> crews) {
        if (crews.size() < MIN_PAIR_SIZE || crews.size() > MAX_PAIR_SIZE) {
            throw PairExceptionMaker.INVALID_CREW_SIZE.makeException();
        }
    }

    public List<String> getCrewNames() {
        return Collections.unmodifiableList(crewNames);
    }
}
