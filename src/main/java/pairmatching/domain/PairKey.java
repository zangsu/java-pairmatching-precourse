package pairmatching.domain;

public class PairKey {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairKey(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public boolean isLevelOf(Level level){
        return this.level == level;
    }

}
