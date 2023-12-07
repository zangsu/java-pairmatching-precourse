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

class PairKeyTest {

    @Nested
    @DisplayName("생성 테스트")
    static class 생성_테스트 {

        static Stream<List<String>> normalKeyArgs() {
            return Stream.of(
                    Arrays.asList("백엔드", "레벨1", "로또"),
                    Arrays.asList("백엔드", "레벨2", "장바구니"),
                    Arrays.asList("프론트엔드", "레벨4", "배포")
            );
        }

        @ParameterizedTest
        @MethodSource("normalKeyArgs")
        @DisplayName("정상 생성 테스트")
        void 정상_생성_테스트(List<String> keyArgs) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> PairKey.of(keyArgs.get(0), keyArgs.get(1), keyArgs.get(2)));
        }

        @Test
        @DisplayName("존재하지 않는 코스 이름일 경우 예외 발생")
        void 존재하지_않는_코스_이름일_경우_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> PairKey.of("안드로이드", "레벨1", "로또"))
                    .withMessage(PairExceptionMaker.INVALID_COURSE.getMessage());
        }

        @Test
        @DisplayName("존재하지 않는 레벨 이름일 경우 예외 발생")
        void 존재하지_않는_레벨_이름일_경우_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> PairKey.of("백엔드", "레벨0", "로또"))
                    .withMessage(PairExceptionMaker.NO_SUCH_LEVEL.getMessage());
        }

        @Test
        @DisplayName("존재하지 않는 미션 이름일 경우 예외 발생")
        void 존재하지_않는_미션_이름일_경우_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> PairKey.of("백엔드", "레벨1", "오징어게임"))
                    .withMessage(PairExceptionMaker.NO_SUCH_MISSION.getMessage());
        }

        @Test
        @DisplayName("해당_레벨에_존재하지_않는_미션일_경우_예외_발생")
        void 해당_레벨에_존재하지_않는_미션일_경우_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> PairKey.of("백엔드", "레벨1", "장바구니"))
                    .withMessage(PairExceptionMaker.NO_SUCH_MISSION.getMessage());
        }

    }

    @Nested
    @DisplayName("레벨 비교 테스트")
    static class 레벨_비교_테스트 {
        private static final PairKey pairKey = PairKey.of("백엔드", "레벨1", "로또");

        @Test
        @DisplayName("레벨이 같을 경우 true 반환")
        void 레벨이_같을_경우_true_반환() {
            Assertions.assertThat(pairKey.isSameLevel(PairKey.of("프론트엔드", "레벨1", "숫자야구게임")))
                    .isTrue();
        }

        @Test
        @DisplayName("레벨이 다를 경우 false 반환")
        void 레벨이_다를_경우_false_반환() {
            Assertions.assertThat(pairKey.isSameLevel(PairKey.of("백엔드", "레벨2", "장바구니")))
                    .isFalse();
        }

    }

    @Nested
    @DisplayName("equals() 테스트")
    static class equals_테스트 {

        public static final PairKey givenKey = PairKey.of("백엔드", "레벨1", "로또");

        static Stream<PairKey> differentPairKeyArgs() {
            return Stream.of(
                    PairKey.of("프론트엔드", "레벨1", "로또"),
                    PairKey.of("백엔드", "레벨2", "장바구니"),
                    PairKey.of("백엔드", "레벨1", "숫자야구게임")
            );
        }

        @Test
        @DisplayName("같은 코스, 레벨, 미션일 경우 true 반환")
        void 같은_코스_레벨_미션일_경우_true_반환() {
            Assertions.assertThat(givenKey.equals(PairKey.of("백엔드", "레벨1", "로또")))
                    .isTrue();
        }

        @ParameterizedTest
        @MethodSource("differentPairKeyArgs")
        @DisplayName("셋 중 하나라도 다르면 false 반환")
        void 셋_중_하나라도_다르면_false_반환(PairKey pairKey) {
            Assertions.assertThat(givenKey)
                    .isNotEqualTo(pairKey);
        }

    }
}