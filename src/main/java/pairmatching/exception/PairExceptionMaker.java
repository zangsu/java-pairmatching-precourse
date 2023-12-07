package pairmatching.exception;

public enum PairExceptionMaker {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    NO_SUCH_MENU("잘못된 메뉴 입력입니다."),
    NO_SUCH_FILE("존재하지 않는 파일입니다."),
    NO_SUCH_LEVEL("존재하지 않는 레벨입니다."),
    NO_SUCH_MISSION("존재하지 않는 미션입니다."),
    INVALID_CREW_SIZE("크루는 2명 또는 3명이어야 합니다."),
    PAIR_CREW_COURSE_DIFFERENT("크루들의 코스가 다릅니다."),
    FAIL_TO_MATCHING("매칭에 실패했습니다."),
    NOT_MATCHED_PAIR("매칭 이력이 없습니다."),
    INVALID_COURSE("존재하지 않는 코스입니다.");

    private final String message;
    private final IllegalArgumentException exception;

    PairExceptionMaker(String message) {
        this.message = message;
        this.exception = new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException makeException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
    }
