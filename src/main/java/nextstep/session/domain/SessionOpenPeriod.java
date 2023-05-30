package nextstep.session.domain;

import static java.time.LocalDateTime.now;
import static nextstep.session.domain.SessionProgressStatus.OPEN;
import static nextstep.session.domain.SessionProgressStatus.sessionStatusByTime;

import java.time.LocalDateTime;

public class SessionOpenPeriod {

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public SessionOpenPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
