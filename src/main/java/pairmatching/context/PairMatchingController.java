package pairmatching.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.matcher.PairMatcher;
import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.exception.PairExceptionMaker;
import pairmatching.exception.handler.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatcher pairMatcher;

    private static final Map<Menu, Runnable> function = new HashMap<>();

    public PairMatchingController(PairMatcher pairMatcher) {
        this.pairMatcher = pairMatcher;
        function.put(Menu.PAIR_MATCHING, this::pairMatching);
        function.put(Menu.FIND_PAIR, this::findPair);
        function.put(Menu.CLEAR, this::clear);
    }

    public void run() {
        RetryHandler.runOrRetry(this::_run);
    }

    private void _run() {
        while (true) {
            Menu menu = inputView.getMenu();
            if (menu == Menu.EXIT) {
                return;
            }
            function.get(menu).run();
        }
    }

    private void pairMatching() {
        outputView.printMissions();
        while (true) {
            PairKey pairKey = RetryHandler.getOrRetry(inputView::getPairKey);
            if (Pairs.notMatched(pairKey) || inputView.reMatch()) {
                retryMatching(pairKey);
                outputView.printPairs(Pairs.getPairs(pairKey));
                return;
            }
        }
    }

    private void retryMatching(PairKey pairKey) {
        for (int i = 0; i < 3; i++) {
            List<Pair> matchedPair = pairMatcher.match(pairKey.getCourse());
            if (Pairs.matching(pairKey, matchedPair)) {
                return;
            }
        }
        throw PairExceptionMaker.FAIL_TO_MATCHING.makeException();
    }

    private void findPair() {
        outputView.printMissions();
        PairKey pairKey = inputView.getPairKey();
        List<Pair> pairs = Pairs.getPairs(pairKey);
        outputView.printPairs(pairs);
    }

    private void clear() {
        Pairs.clear();
        outputView.clear();
    }
}
