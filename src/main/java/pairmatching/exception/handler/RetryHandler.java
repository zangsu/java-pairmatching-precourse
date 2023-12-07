package pairmatching.exception.handler;

import java.util.Arrays;
import java.util.function.Supplier;
import pairmatching.exception.PairExceptionMaker;
import pairmatching.view.OutputView;

/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler{
    private static final OutputView outputView = new OutputView();

    public static <T> T getOrRetry(Supplier<T> supplier){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                outputView.printException(e);
            }
        }
    }
    public static <T> T getOrConditionalRetry(Supplier<T> supplier, PairExceptionMaker... expectedExceptions){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                checkExpectedException(e, expectedExceptions);
            }
        }
    }
    public static void runOrRetry(Runnable runnable) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }
    public static void runOrConditionalRetry(Runnable runnable, PairExceptionMaker... expectedExceptions) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                checkExpectedException(e, expectedExceptions);
            }
        }
    }

    private static void checkExpectedException(IllegalArgumentException e, PairExceptionMaker[] expectedExceptions) {
        if(!isExpectedException(e, expectedExceptions)){
            throw e;
        }
        outputView.printException(e);
    }

    private static boolean isExpectedException(IllegalArgumentException e, PairExceptionMaker[] exceptions) {
        return Arrays.stream(exceptions)
                .map(PairExceptionMaker::makeException)
                .anyMatch(e::equals);
    }
}
