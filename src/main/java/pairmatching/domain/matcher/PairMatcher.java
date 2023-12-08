package pairmatching.domain.matcher;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.pair.Pair;
import pairmatching.repository.Crews;

public class PairMatcher {
    private final MatchStrategy matchStrategy;

    public PairMatcher(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public List<Pair> match(Course course) {
        List<String> crewNames = Crews.getCrewNamesOf(course);
        return matchStrategy.match(crewNames);
    }
}
