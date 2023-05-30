package nextstep.session.domain;

import static java.time.LocalDateTime.now;
import static nextstep.session.domain.SessionProgressStatus.OPEN;

import java.time.LocalDateTime;

public class SessionOpenPeriod {

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public SessionOpenPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionProgressStatus sessionProgressStatus() {
        return SessionProgressStatus.sessionStatusByTime(startDate,endDate, now());
    }

    private boolean isSessionOpen() {
        return sessionProgressStatus() == OPEN;
    }

    public boolean isEnrollAvailable() {
        return isSessionOpen();
    }
}
