package pairmatching.domain.pair;

import java.util.List;
import java.util.stream.Collectors;

public class Pairs {
    private final List<Pair> pairs;

    public Pairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public boolean contains(Pair pair) {
        return pairs.contains(pair);
    }

    public boolean hasDuplicatedPair(Pairs other) {
        return pairs.stream()
                .anyMatch(other::contains);
    }

    public List<List<String>> getPairNames() {
        return pairs.stream()
                .map(Pair::getCrewNames)
                .collect(Collectors.toList());
    }
}
