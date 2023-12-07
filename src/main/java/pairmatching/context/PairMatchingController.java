package pairmatching.context;

import java.util.List;
import pairmatching.domain.matcher.PairMatcher;
import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
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
        while(true){
            Menu menu = inputView.getMenu();
            if (menu == Menu.EXIT) {
                break;
            }
            if (menu == Menu.PAIR_MATCHING){
                RetryHandler.runOrRetry(this::pairMatching);
            }
            if (menu == Menu.FIND_PAIR){
                //findPair();
            }
            if (menu == Menu.CLEAR){
                //clear();
            }
        }
    }

    private void pairMatching() {
        outputView.printMissions();
        while(true) {
            PairKeyDto pairKeyDto = inputView.getPairKey();
            PairKey pairKey = PairKey.from(pairKeyDto);
            if(Pairs.notMatched(pairKey) || inputView.reMatch()){
                List<Pair> matchedPair = pairMatcher.match(pairKey.getCourse());
                Pairs.matching(pairKey, matchedPair);
                return;
            }
        }
    }
}
