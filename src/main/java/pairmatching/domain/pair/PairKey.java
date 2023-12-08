package pairmatching.domain.pair;

import java.util.Objects;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class PairKey {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairKey(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static PairKey of(String courseString, String levelString, String missionString) {
        Course course = Course.from(courseString);
        Level level = Level.from(levelString);
        Mission mission = level.getMission(missionString);
        return new PairKey(course, level, mission);
    }

    public boolean isSameLevel(PairKey pairKey) {
        return this.level == pairKey.level;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairKey pairKey = (PairKey) o;
        return course == pairKey.course && level == pairKey.level && mission == pairKey.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
