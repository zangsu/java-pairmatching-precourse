package pairmatching.domain.matcher;

import java.util.List;
import pairmatching.domain.pair.Pair;

@FunctionalInterface
public interface MatchStrategy {

    List<Pair> match(List<String> crewNames);
}
