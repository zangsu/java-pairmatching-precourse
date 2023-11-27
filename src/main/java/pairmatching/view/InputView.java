package pairmatching.view;

import java.util.List;
import pairmatching.view.io.Printer;
import pairmatching.view.io.Reader;

public class InputView {
    private final List<String> menus = List.of("1", "2", "3", "Q");
    private final Printer printer = new Printer();
    private final Reader reader = new Reader();

    public String getMenu() {
        printer.printMessage("""
                기능을 선택하세요.
                1. 페어 매칭
                2. 페어 조회
                3. 페어 초기화
                Q. 종료""");
        String userMenu = reader.getString();
        assertUserMenu(userMenu);
        return userMenu;
    }

    private void assertUserMenu(String userMenu) {
        if(!menus.contains(userMenu)){
            //todo
            throw new IllegalArgumentException("잘못된 메뉴 입력");
        }
    }

    public List<String> pairMatchingView(){
        printer.printMessage("""
                #############################################
                과정: 백엔드 | 프론트엔드
                미션:
                  - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                  - 레벨2: 장바구니 | 결제 | 지하철노선도
                  - 레벨3:
                  - 레벨4: 성능개선 | 배포
                  - 레벨5:
                ############################################
                과정, 레벨, 미션을 선택하세요.
                ex) 백엔드, 레벨1, 자동차경주""");
        return reader.getStringsWithDelimiter(",");
    }
}
