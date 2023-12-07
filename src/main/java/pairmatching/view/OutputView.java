package pairmatching.view;

import java.util.List;
import pairmatching.domain.pair.Pair;
import pairmatching.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    private final Printer printer = new Printer();

    public void printException(Exception e){
        printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public void newLine(){
        printer.printMessage("");
    }

    public void printMissions(){
        printer.printMessage("#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################");
    }
    public void printPairs(List<Pair> pairs) {
        printer.printMessage("페어 매칭 결과입니다.");
        pairs.stream()
                .map(Pair::getCrewNames)
                .map(names -> String.join(" : ", names))
                .forEach(printer::printMessage);
        newLine();
    }

    public void clear() {
        printer.printMessage("초기화 되었습니다. ");
        newLine();
    }
}
