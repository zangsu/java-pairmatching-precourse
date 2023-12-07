package pairmatching.domain.pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pairs {
    private static final Map<PairKey, List<Pair>> pairs = new HashMap<>();

    public static boolean notMatched(PairKey pairKey) {
        return !pairs.containsKey(pairKey);
    }

    public static void matching(PairKey pairKey, List<Pair> matchedPair) {
        pairs.put(pairKey, matchedPair);
    }
}
