package pairmatching.view.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import pairmatching.exception.PairExceptionMaker;

public class Reader {
    public int getInteger(){
        String input = Console.readLine();
        return parseInt(input);
    }

    private int parseInt(String input) {
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw PairExceptionMaker.INVALID_NUMBER_FORMAT.makeException();
        }
    }

    public String getString(){
        return Console.readLine();
    }

    public List<String> getStringsUsingDelimiter(String delimiter) {
        String input = Console.readLine();
        validateNotEndDelimiter(input, delimiter);
        List<String> inputs = Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
        inputs.forEach(this::validateNotBlank);
        return inputs;
    }

    private void validateNotBlank(String input){
        if(input.isEmpty()){
            throw PairExceptionMaker.BLANK_INPUT.makeException();
        }
    }
    private void validateNotEndDelimiter(String input, String delimiter) {
        if (input.endsWith(delimiter)) {
            throw PairExceptionMaker.INVALID_INPUT_FORMAT.makeException();
        }
    }

    public boolean getBoolean(String trueMessage, String falseMessage){
        String input = Console.readLine().trim();
        if(trueMessage.equals(input)){
            return true;
        }
        if(falseMessage.equals(input)){
            return false;
        }
        throw PairExceptionMaker.INVALID_INPUT_FORMAT.makeException();
    }

    //아래 메서드는 안쓸 것 같긴 한데
    public <T> List<T> getObjectsUsingDelimiter(Function<String, T> converter, String delimiter){
        List<String> inputStrings = getStringsUsingDelimiter(delimiter);
        return inputStrings.stream()
                .map(converter::apply)
                .collect(Collectors.toList());
    }
}
