package pairmatching.domain.pair;

import java.util.HashMap;
import java.util.Map;

public class Pairs {
    private static final Map<PairKey, Pair> pairs = new HashMap<>();

    public static boolean notMatched(PairKey pairKey) {
        return !pairs.containsKey(pairKey);
    }
}
