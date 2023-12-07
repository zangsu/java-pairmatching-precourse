package pairmatching.domain.pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import pairmatching.exception.PairExceptionMaker;

public class Pairs {
    //todo 얘를 static으로 만드는게 맞나
    private static final Map<PairKey, List<Pair>> pairs = new HashMap<>();

    public static boolean notMatched(PairKey pairKey) {
        return !pairs.containsKey(pairKey);
    }

    public static boolean matchSuccess(PairKey pairKey, List<Pair> matchedPair) {
        List<Pair> sameLevelPairs = pairs.keySet().stream()
                .filter(key -> key.isSameLevel(pairKey))
                .map(pairs::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        if (duplicated(matchedPair, sameLevelPairs)) {
            return false;
        }
        pairs.put(pairKey, matchedPair);
        return true;
    }

    private static boolean duplicated(List<Pair> matchedPair, List<Pair> sameLevelPairs) {
        return matchedPair.stream()
                .anyMatch(sameLevelPairs::contains);
    }

    public static List<Pair> getPairs(PairKey pairKey) {
        return Optional.ofNullable(pairs.get(pairKey))
                .orElseThrow(PairExceptionMaker.NOT_MATCHED_PAIR::makeException);
    }

    public static void clear() {
        pairs.clear();
    }
}
