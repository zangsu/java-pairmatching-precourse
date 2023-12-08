package pairmatching.domain.pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pairmatching.exception.PairExceptionMaker;

class PairTest {

    @Nested
    @DisplayName("생성 테스트")
    static class 생성_테스트 {

        static Stream<List<String>> normalPairCrews() {
            return Stream.of(
                    Arrays.asList("a", "b"),
                    Arrays.asList("a", "b", "c")
            );
        }

        @ParameterizedTest
        @MethodSource("normalPairCrews")
        @DisplayName("정상 생성 테스트")
        void 정상_생성_테스트(List<String> crewNames) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Pair(crewNames));
        }

        @Test
        @DisplayName("크루 이름이 2개 미만일 경우 예외 발생")
        void 크루_이름이_2개_미만일_경우_예외_발생() {
            List<String> crewNames = Arrays.asList("a");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Pair(crewNames))
                    .withMessage(PairExceptionMaker.INVALID_CREW_SIZE.getMessage());
        }

        @Test
        @DisplayName("크루 이름이 3개 초과일 경우 예외 발생")
        void 크루_이름이_3개_초과일_경우_예외_발생() {
            List<String> crewNames = Arrays.asList("a", "b", "c", "d");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Pair(crewNames))
                    .withMessage(PairExceptionMaker.INVALID_CREW_SIZE.getMessage());
            ;
        }

        @Test
        @DisplayName("크루 이름이 중복될 경우 예외 발생")
        void 크루_이름이_중복될_경우_예외_발생() {
            List<String> crewNames = Arrays.asList("a", "a");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Pair(crewNames))
                    .withMessage(PairExceptionMaker.DUPLICATED_CREW_NAME.getMessage());
        }

    }
}