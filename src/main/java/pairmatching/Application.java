package pairmatching;

import pairmatching.context.PairMatchingController;
import pairmatching.domain.matcher.PairMatcher;
import pairmatching.domain.matcher.ShuffledMatcher;

public class Application {
    public static void main(String[] args) {
        PairMatcher shuffledPairMatcher = new PairMatcher(new ShuffledMatcher());
        PairMatchingController controller = new PairMatchingController(shuffledPairMatcher);
        controller.run();
    }
}
