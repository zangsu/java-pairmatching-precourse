package pairmatching.domain.pair;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairsTest {

    private static final Pairs givenPairs = new Pairs(Arrays.asList(
            new Pair(Arrays.asList("a", "b")),
            new Pair(Arrays.asList("c", "d")),
            new Pair(Arrays.asList("e", "f"))
    ));

    @Test
    @DisplayName("중복된 페어가 존재할 경우 true 반환")
    void 중복된_페어가_존재할_경우_true_반환() {
        Pairs otherPairs = new Pairs(Arrays.asList(
                new Pair(Arrays.asList("a", "c")),
                new Pair(Arrays.asList("d", "b")),
                new Pair(Arrays.asList("e", "f"))
        ));
        Assertions.assertThat(givenPairs.hasDuplicatedPair(otherPairs))
                .isTrue();
    }

    @Test
    @DisplayName("중복된 페어가 존재하지 않을 경우 false 반환")
    void 중복된_페어가_존재하지_않을_경우_false_반환() {
        Pairs otherPairs = new Pairs(Arrays.asList(
                new Pair(Arrays.asList("a", "f")),
                new Pair(Arrays.asList("c", "b")),
                new Pair(Arrays.asList("e", "d"))
        ));
        Assertions.assertThat(givenPairs.hasDuplicatedPair(otherPairs))
                .isFalse();
    }
}