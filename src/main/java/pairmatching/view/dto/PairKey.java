package pairmatching.view.dto;

public class PairKey {
    private final String Course;
    private final String Level;
    private final String mission;

    public PairKey(String course, String level, String mission) {
        Course = course;
        Level = level;
        this.mission = mission;
    }

    public String getCourse() {
        return Course;
    }

    public String getLevel() {
        return Level;
    }

    public String getMission() {
        return mission;
    }
}
