package pairmatching.view;

import java.util.List;
import pairmatching.domain.menu.Menu;
import pairmatching.domain.pair.PairKey;
import pairmatching.exception.PairExceptionMaker;
import pairmatching.exception.handler.RetryHandler;
import pairmatching.view.io.Printer;
import pairmatching.view.io.Reader;

public class InputView {

    public static final String DELIMITER = ",";
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();

    public Menu getMenu() {
        printer.printMessage("기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
        return RetryHandler.getOrRetry(this::_getMenu);
    }

    private Menu _getMenu() {
        Menu menu = Menu.from(reader.getString());
        printer.printMessage("");
        return menu;
    }

    public PairKey getPairKey() {
        return RetryHandler.getOrRetry(this::_getPairKey);
    }

    private PairKey _getPairKey() {
        printer.printMessage("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주");
        List<String> inputs = reader.getStringsUsingDelimiter(DELIMITER);
        printer.printMessage("");
        if (inputs.size() != 3) {
            throw PairExceptionMaker.INVALID_INPUT.makeException();
        }
        return PairKey.of(inputs.get(0), inputs.get(1), inputs.get(2));
    }

    public boolean reMatch() {
        return RetryHandler.getOrRetry(() -> {
            printer.printMessage("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                    + "네 | 아니오");
            boolean restart = reader.getBoolean("네", "아니오");
            printer.printMessage("");
            return restart;
        });
    }
}
