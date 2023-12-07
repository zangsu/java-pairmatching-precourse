package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import pairmatching.exception.PairExceptionMaker;

public enum Level {
    LEVEL1("레벨1", Mission.CAR_RACING, Mission.LOTTO, Mission.NUMBER_BASEBALL),
    LEVEL2("레벨2", Mission.SHOPPING_CART, Mission.PAYMENT, Mission.SUBWAY_MAP),
    LEVEL3("레벨3"),
    LEVEL4("레벨4", Mission.DEPLOYMENT, Mission.PERFORMANCE_IMPROVEMENT),
    LEVEL5("레벨5");

    private static final Map<String, Level> cachedLevel = Arrays.stream(values())
            .collect(Collectors.toMap(level -> level.name, level -> level));
    private final String name;
    private final List<Mission> missions;

    Level(String name, Mission... missions) {
        this.name = name;
        this.missions = Arrays.asList(missions);
    }

    public static Level from(String name){
        return Optional.ofNullable(cachedLevel.get(name))
                .orElseThrow(PairExceptionMaker.NO_SUCH_LEVEL::makeException);
    }

    public Mission getMission(String missionName){
        return missions.stream()
                .filter(mission -> mission.isNameOf(missionName))
                .findFirst()
                .orElseThrow(PairExceptionMaker.NO_SUCH_MISSION::makeException);
    }

    // 추가 기능 구현
}