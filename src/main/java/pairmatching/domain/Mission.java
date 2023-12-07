package pairmatching.domain;

public enum Mission {

    // 레벨 1
    CAR_RACING("자동차경주"),
    LOTTO("로또"),
    NUMBER_BASEBALL("숫자야구게임"),

    // 레벨 2
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY_MAP("지하철노선도"),

    // 레벨 4
    PERFORMANCE_IMPROVEMENT("성능개선"),
    DEPLOYMENT("배포");

    private final String missionName;

    Mission(String missionName) {
        this.missionName = missionName;
    }

    public boolean isNameOf(String missionName) {
        return this.missionName.equals(missionName);
    }
}
