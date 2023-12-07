package pairmatching.controller;

import pairmatching.domain.menu.Menu;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

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

            }
            if (menu == Menu.FIND_PAIR){

            }
            if (menu == Menu.CLEAR){

            }
        }
    }
}
