package pairmatching.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pairmatching.domain.matcher.PairMatcher;
import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.exception.PairExceptionMaker;
import pairmatching.exception.handler.RetryHandler;
import pairmatching.repository.MatchedPairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private static final int MAX_REMATCH_COUNT = 3;
    private final Map<Menu, Runnable> function = new HashMap<>();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatcher pairMatcher;

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
            Optional.ofNullable(function.get(menu))
                    .orElseThrow(PairExceptionMaker.CANT_RUN_MENU::makeException)
                    .run();
        }
    }

    private void pairMatching() {
        outputView.printMissions();
        while (true) {
            PairKey pairKey = RetryHandler.getOrRetry(inputView::getPairKey);
            if (MatchedPairs.isNotMatched(pairKey) || inputView.reMatch()) {
                retryMatching(pairKey);
                outputView.printPairs(MatchedPairs.getPairs(pairKey));
                return;
            }
        }
    }

    private void retryMatching(PairKey pairKey) {
        for (int i = 0; i < MAX_REMATCH_COUNT; i++) {
            Pairs matchedPair = new Pairs(pairMatcher.match(pairKey.getCourse()));
            if (MatchedPairs.matchSuccess(pairKey, matchedPair)) {
                return;
            }
        }
        throw PairExceptionMaker.FAIL_TO_MATCHING.makeException();
    }

    private void findPair() {
        outputView.printMissions();
        PairKey pairKey = inputView.getPairKey();
        Pairs pairs = MatchedPairs.getPairs(pairKey);
        outputView.printPairs(pairs);
    }

    private void clear() {
        MatchedPairs.clear();
        outputView.clear();
    }
}
