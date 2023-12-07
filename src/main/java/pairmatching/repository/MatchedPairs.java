package pairmatching.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.exception.PairExceptionMaker;

public class MatchedPairs {
    private static final Map<PairKey, Pairs> pairs = new HashMap<>();

    private MatchedPairs() {

    }

    public static boolean isNotMatched(PairKey pairKey) {
        return !pairs.containsKey(pairKey);
    }

    public static boolean matchSuccess(PairKey pairKey, Pairs matchedPair) {
        if (hasDuplicatedPair(pairKey, matchedPair)) {
            return false;
        }
        pairs.put(pairKey, matchedPair);
        return true;
    }

    private static boolean hasDuplicatedPair(PairKey pairKey, Pairs matchedPair) {
        return pairs.keySet().stream()
                .filter(key -> key.isSameLevel(pairKey))
                .map(pairs::get)
                .anyMatch(matchedPair::hasDuplicatedPair);
    }

    public static Pairs getPairs(PairKey pairKey) {
        return Optional.ofNullable(pairs.get(pairKey))
                .orElseThrow(PairExceptionMaker.NOT_MATCHED_PAIR::makeException);
    }

    public static void clear() {
        pairs.clear();
    }
}
