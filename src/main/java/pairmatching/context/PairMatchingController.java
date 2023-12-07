package pairmatching.context;

import java.util.List;
import pairmatching.domain.matcher.PairMatcher;
import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.exception.PairExceptionMaker;
import pairmatching.exception.handler.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.dto.PairKeyDto;

public class PairMatchingController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatcher pairMatcher;

    public PairMatchingController(PairMatcher pairMatcher) {
        this.pairMatcher = pairMatcher;
    }

    public void run(){
        RetryHandler.runOrRetry(this::_run);
    }

    public void _run(){
        while(true){
            Menu menu = inputView.getMenu();
            if (menu == Menu.EXIT) {
                return;
            }
            if (menu == Menu.PAIR_MATCHING){
                this.pairMatching();
            }
            if (menu == Menu.FIND_PAIR){
                findPair();
            }
            if (menu == Menu.CLEAR){
                //clear();
            }
        }
    }

    private void findPair() {
        PairKey pairKey = RetryHandler.getOrRetry(inputView::getPairKey);
        List<Pair> pairs = Pairs.getPairs(pairKey);
        outputView.printPairs(pairs);
    }

    private void pairMatching() {
        while(true){
            PairKey pairKey = RetryHandler.getOrRetry(inputView::getPairKey);
            if(Pairs.notMatched(pairKey) || inputView.reMatch()){
                retryMatching(pairKey);
                outputView.printPairs(Pairs.getPairs(pairKey));
                return;
            }
        }
    }

    private void retryMatching(PairKey pairKey) {
        for (int i = 0; i < 3; i++) {
            List<Pair> matchedPair = pairMatcher.match(pairKey.getCourse());
            if(Pairs.matching(pairKey, matchedPair)){
                return;
            }
        }
        throw PairExceptionMaker.FAIL_TO_MATCHING.makeException();
    }
}
