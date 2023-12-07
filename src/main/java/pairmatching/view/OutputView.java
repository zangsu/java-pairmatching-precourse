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

    private <T> void printListUsingFormat(List<T> list){
        list.forEach(t -> printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    }

    public void printPairs(List<Pair> pairs) {
    }
}
