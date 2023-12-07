package pairmatching.domain.matcher;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import pairmatching.domain.pair.Pair;

public class ShuffledMatcher implements MatchStrategy {

    @Override
    public List<Pair> match(List<String> crewNames) {
        List<Pair> result = new ArrayList<>();
        Queue<String> shuffledCrewNames = new LinkedList<>(Randoms.shuffle(crewNames));

        while (shuffledCrewNames.size() > Pair.MAX_PAIR_SIZE) {
            List<String> pair = new ArrayList<>();
            pair.add(shuffledCrewNames.poll());
            pair.add(shuffledCrewNames.poll());
            result.add(new Pair(pair));
        }
        result.add(new Pair(shuffledCrewNames));

        return result;
    }
}
