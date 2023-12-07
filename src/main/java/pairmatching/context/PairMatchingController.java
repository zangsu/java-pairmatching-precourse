package pairmatching.context;

import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.dto.PairKeyDto;

public class PairMatchingController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run(){
        while(true){
            Menu menu = inputView.getMenu();
            if (menu == Menu.EXIT) {
                break;
            }
            if (menu == Menu.PAIR_MATCHING){
                pairMatching();
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
                //매칭한다.
                return;
            }
        }
    }
}
