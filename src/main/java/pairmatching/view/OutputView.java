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

    public void printPairs(List<Pair> pairs) {
        printer.printMessage("페어 매칭 결과입니다.");
        pairs.stream()
                .map(Pair::getCrewNames)
                .map(names -> String.join(" : ", names))
                .forEach(printer::printMessage);
        newLine();
    }
}
