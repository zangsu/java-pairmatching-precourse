package pairmatching.repository;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairKey;
import pairmatching.domain.pair.Pairs;
import pairmatching.exception.PairExceptionMaker;

class MatchedPairsTest {

    public static final List<Pair> FIRST_LOTTO_PAIR = Arrays.asList(new Pair(Arrays.asList("a", "b")),
            new Pair(Arrays.asList("c", "d")));

    @BeforeEach
    void setUp() {
        MatchedPairs.clear();
    }

    @Nested
    @DisplayName("매칭 여부 테스트")
    class 매칭_여부_테스트 {
        @Test
        @DisplayName("아직 매칭되지 않은 미션에 대해 true 반환")
        void 아직_매칭되지_않은_미션에_대해_true_반환() {
            Assertions.assertThat(MatchedPairs.isNotMatched(PairKey.of("백엔드", "레벨1", "로또")))
                    .isTrue();
        }

        @Test
        @DisplayName("이미 매칭된 미션에 대해 false 반환")
        void 이미_매칭된_미션에_대해_false_반환() {
            MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "로또"),
                    new Pairs(FIRST_LOTTO_PAIR));
            Assertions.assertThat(MatchedPairs.isNotMatched(PairKey.of("백엔드", "레벨1", "로또")))
                    .isFalse();
        }
    }

    @Nested
    @DisplayName("매칭 저장 테스트")
    class 매칭_저장_테스트 {
        @Test
        @DisplayName("아직 저장된 페어가 없을 경우 true 반환")
        void 아직_저장된_페어가_없을_경우_true_반환() {
            Assertions.assertThat(MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "로또"),
                            new Pairs(FIRST_LOTTO_PAIR)))
                    .isTrue();
        }

        @Test
        @DisplayName("중복된 페어가 존재할 경우 false 반환")
        void 중복된_페어가_존재할_경우_false_반환() {
            MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "로또"),
                    new Pairs(FIRST_LOTTO_PAIR));
            Assertions.assertThat(MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "숫자야구게임"),
                            new Pairs(FIRST_LOTTO_PAIR)))
                    .isFalse();
        }

        @Test
        @DisplayName("중복된 페어가 존재하지 않을 경우 true 반환")
        void 중복된_페어가_존재하지_않을_경우_true_반환() {
            MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "로또"),
                    new Pairs(FIRST_LOTTO_PAIR));
            Assertions.assertThat(MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "숫자야구게임"),
                            new Pairs(Arrays.asList(new Pair(Arrays.asList("a", "d")),
                                    new Pair(Arrays.asList("c", "b"))))))
                    .isTrue();
        }
    }

    @Nested
    @DisplayName("매칭 조회 테스트")
    class 매칭_조회_테스트 {
        @Test
        @DisplayName("매칭된 페어 조회")
        void 매칭된_페어_조회() {
            MatchedPairs.matchSuccess(PairKey.of("백엔드", "레벨1", "로또"),
                    new Pairs(FIRST_LOTTO_PAIR));
            Assertions.assertThat(MatchedPairs.getPairs(PairKey.of("백엔드", "레벨1", "로또"))
                            .getPairNames())
                    .isEqualTo(new Pairs(FIRST_LOTTO_PAIR).getPairNames());
        }

        @Test
        @DisplayName("매칭되지 않은 페어 조회")
        void 매칭되지_않은_페어_조회() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> MatchedPairs.getPairs(PairKey.of("백엔드", "레벨1", "로또")))
                    .withMessage(PairExceptionMaker.NOT_MATCHED_PAIR.getMessage());
        }
    }
}