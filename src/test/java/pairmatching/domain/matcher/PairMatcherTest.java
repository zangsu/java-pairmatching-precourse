package pairmatching.domain.matcher;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Course;
import pairmatching.domain.pair.Pair;

class PairMatcherTest {

    public static final PairMatcher FIXED_MATCHER = new PairMatcher(new FixedMatcher());

    @Test
    @DisplayName("짝수 크루 매칭 정상 결과 확인")
    void 크루_매칭_정상_결과_확인() {
        List<Pair> match = FIXED_MATCHER.match(Course.BACKEND);
        List<Pair> expectedPairs = Arrays.asList(
                new Pair(Arrays.asList("백호", "태웅")),
                new Pair(Arrays.asList("치수", "태섭")),
                new Pair(Arrays.asList("대만", "준호")),
                new Pair(Arrays.asList("대협", "덕규")),
                new Pair(Arrays.asList("태산", "경태")),
                new Pair(Arrays.asList("수겸", "현준")),
                new Pair(Arrays.asList("준섭", "한나")),
                new Pair(Arrays.asList("소연", "호열")),
                new Pair(Arrays.asList("대남", "용팔")),
                new Pair(Arrays.asList("구식", "달재"))
        );
        for (int i = 0; i < match.size(); i++) {
            Assertions.assertThat(match.get(i).getCrewNames())
                    .isEqualTo(expectedPairs.get(i).getCrewNames());
        }
    }

    @Test
    @DisplayName("홀수 크루 매칭 정상 결과 확인")
    void 홀수_크루_매칭_정상_결과_확인() {
        List<Pair> match = FIXED_MATCHER.match(Course.FRONTEND);
        List<Pair> expectedPairs = Arrays.asList(
                new Pair(Arrays.asList("보노", "시저")),
                new Pair(Arrays.asList("쉐리", "신디")),
                new Pair(Arrays.asList("다비", "덴버")),
                new Pair(Arrays.asList("이브", "제시")),
                new Pair(Arrays.asList("라라", "린다")),
                new Pair(Arrays.asList("리사", "니콜")),
                new Pair(Arrays.asList("로드", "윌터", "제키"))
        );
        for (int i = 0; i < match.size(); i++) {
            Assertions.assertThat(match.get(i).getCrewNames())
                    .isEqualTo(expectedPairs.get(i).getCrewNames());
        }
    }

}