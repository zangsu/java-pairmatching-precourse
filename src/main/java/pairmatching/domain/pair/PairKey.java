package pairmatching.domain.pair;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.view.dto.PairKeyDto;

public class PairKey {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairKey(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static PairKey from(PairKeyDto pairKeyDto){
        Course course = Course.valueOf(pairKeyDto.getCourse());
        Level level = Level.from(pairKeyDto.getLevel());
        Mission mission = level.getMission(pairKeyDto.getMission());
        return new PairKey(course, level, mission);
    }

    public boolean isLevelOf(Level level){
        return this.level == level;
    }

    public Course getCourse() {
        return course;
    }
}
