package pairmatching.domain.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import pairmatching.exception.PairExceptionMaker;

public enum Menu {
    PAIR_MATCHING("1"),
    FIND_PAIR("2"),
    CLEAR("3"),
    EXIT("Q");

    private static final Map<String, Menu> cachedMenu = Arrays.stream(values())
            .collect(Collectors.toMap(menu -> menu.input, menu -> menu));
    private final String input;

    Menu(String input) {
        this.input = input;
    }

    public static Menu from(String input) {
        return Optional.ofNullable(cachedMenu.get(input))
                .orElseThrow(PairExceptionMaker.NO_SUCH_MENU::makeException);
    }
}
