package nextstep.session.domain;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionOpenPeriodTest {

    private static final LocalDateTime NOW_MINUS_MONTH = now().minusMonths(1);

    private static final LocalDateTime NOW_MINUS_HALF_MONTH = now().minusDays(15);

    private static final LocalDateTime NOW_PLUS_MONTH = now().plusMonths(1);

    private static final LocalDateTime NOW_PLUS_DAY = now().plusDays(1);

    private static final SessionOpenPeriod PREPARING_PERIOD =
            new SessionOpenPeriod(NOW_MINUS_MONTH, NOW_MINUS_HALF_MONTH);

    private static final SessionOpenPeriod OPEN_PERIOD =
            new SessionOpenPeriod(NOW_MINUS_HALF_MONTH, NOW_PLUS_MONTH);


    @Test
    @DisplayName("강의가 아직 열리기 전인경우 수강신청 가능 여부는 false 를 반환한다.")
    void isEnrollAvailableTest() {
        assertThat(PREPARING_PERIOD.isEnrollAvailable()).isFalse();
    }

    @Test
    @DisplayName("강의 시작시간보다 현재 시간이 크고 강의 종료시간보다 작은경우 True 를 반환한다.")
    void isOpenTest() {
        assertThat(OPEN_PERIOD.isEnrollAvailable()).isTrue();
    }

    @Test
    @DisplayName("시작시간이 종료시간보다 클 경우 예외를 발생한다.")
    void invalidTimeTest() {
        assertThatThrownBy(() -> new SessionOpenPeriod(NOW_PLUS_MONTH, NOW_PLUS_DAY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 시작시간이 종료일보다 클 수 없습니다.");
    }

}
