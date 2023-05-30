package nextstep.session.domain;

import java.time.LocalDateTime;

public enum SessionProgressStatus {

    PREPARING,
    OPEN,
    CLOSED,
    ;

    public static SessionProgressStatus getByTime(LocalDateTime from,
            LocalDateTime to,
            LocalDateTime target) {

        if(target.isBefore(from)) {
            return PREPARING;
        }

        if(target.isAfter(to)) {
            return CLOSED;
        }

        return OPEN;
    }
}
