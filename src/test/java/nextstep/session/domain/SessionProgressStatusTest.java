package nextstep.session.domain;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionProgressStatusTest {

    //2023 4월 28일 12시 부터 5월 27일 12시 까지
    private static final LocalDateTime FROM =
            of(2023,4,28,12,0);

    private static final LocalDateTime TO =
            of(2023,5,28,12,0);

    private static final LocalDateTime PREPARING =
            of(2023, 3, 28, 12, 0);

    private static final LocalDateTime OPEN =
            of(2023, 5, 18, 0, 0);

    private static final LocalDateTime CLOSED =
            of(2024, 5, 18, 0, 0);

    @Test
    @DisplayName("현재 시간이 시작시간 이전이면 강의는 준비중 상태이다.")
    void preparingTest() {
        assertThat(SessionProgressStatus.getByTime(FROM,TO,PREPARING))
                .isEqualTo(SessionProgressStatus.PREPARING);
    }

    @Test
    @DisplayName("현재 시간이 시작시간과 종료시간 중간에 있을 경우 강의는 오픈 상태이다.")
    void openTest() {
        assertThat(SessionProgressStatus.getByTime(FROM,TO,OPEN))
                .isEqualTo(SessionProgressStatus.OPEN);
    }

    @Test
    @DisplayName("현재 시간이 종료시간 이후라면 강의는 닫히게 된다.")
    void closedTest() {
        assertThat(SessionProgressStatus.getByTime(FROM,TO,CLOSED))
                .isEqualTo(SessionProgressStatus.CLOSED);
    }
}
