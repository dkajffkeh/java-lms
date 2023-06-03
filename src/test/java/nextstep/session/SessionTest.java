package nextstep.session;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.session.domain.Session;
import nextstep.session.domain.enrollment.SessionOpenPeriod;
import nextstep.session.domain.enrollment.Students;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    private Session closedSession;

    private Session openSession;

    @BeforeEach
    void init() {
        closedSession = new Session(null, new FakeClosed(now(), now()),
                null, null, new Students(new ArrayList<>(), 10));

        openSession = new Session(null, new FakeOpen(now(), now()),
                null, null, new Students(new ArrayList<>(), 10));
    }

    @Test
    @DisplayName("강의가 수강이 가능한 상태가 아닌경우 Exception 을 발생한다.")
    void enrollSessionClosedTest() {
        assertThatThrownBy(() -> closedSession.enrollSession(new NsUser()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지금은 수강신청 가능 기간이 아닙니다.");
    }

    @Test
    @DisplayName("수강신청이 열려 있는 상태여야지만 수강신청이 가능하다")
    void enrollSessionOpenTest() {
        assertDoesNotThrow(() -> openSession.enrollSession(new NsUser()));
    }

    private static class FakeOpen extends SessionOpenPeriod {

        public FakeOpen(LocalDateTime startDate, LocalDateTime endDate) {
            super(startDate, endDate);
        }

        @Override
        public boolean isEnrollAvailable() {
            return true;
        }
    }

    private static class FakeClosed extends SessionOpenPeriod {

        public FakeClosed(LocalDateTime startDate, LocalDateTime endDate) {
            super(startDate, endDate);
        }

        @Override
        public boolean isEnrollAvailable() {
            return false;
        }
    }

}
