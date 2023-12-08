package pairmatching.domain.matcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import pairmatching.domain.pair.Pair;

public class FixedMatcher implements MatchStrategy {

    @Override
    public List<Pair> match(List<String> crewNames) {
        List<Pair> result = new ArrayList<>();
        Queue<String> crewNameQueue = new LinkedList<>(crewNames);
        while (crewNameQueue.size() > Pair.MAX_PAIR_SIZE) {
            List<String> pair = new ArrayList<>();
            pair.add(crewNameQueue.poll());
            pair.add(crewNameQueue.poll());
            result.add(new Pair(pair));
        }
        result.add(new Pair(crewNameQueue));
        return result;
    }
}
