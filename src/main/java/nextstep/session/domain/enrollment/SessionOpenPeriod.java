package nextstep.session.domain.enrollment;

import static java.time.LocalDateTime.now;
import static nextstep.session.domain.SessionProgressStatus.OPEN;
import static nextstep.session.domain.SessionProgressStatus.sessionStatusByTime;

import java.time.LocalDateTime;
import nextstep.session.domain.SessionProgressStatus;

public class SessionOpenPeriod {

    private static final String START_BIGGER_THAN_END_MSG = "강의 시작시간이 종료일보다 클 수 없습니다.";

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public SessionOpenPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if(isInvalidTime(startDate, endDate)) {
            throw new IllegalArgumentException(START_BIGGER_THAN_END_MSG);
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private boolean isInvalidTime(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.isAfter(endDate);
    }

    private SessionProgressStatus sessionProgressStatus() {
        return sessionStatusByTime(startDate,endDate, now());
    }

    private boolean isSessionOpen() {
        return sessionProgressStatus() == OPEN;
    }

    public boolean isEnrollAvailable() {
        return isSessionOpen();
    }
}
