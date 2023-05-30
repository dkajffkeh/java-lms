package nextstep.session.domain;

import static nextstep.session.domain.Students.MAX_ENROLLMENT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentsTest {

    private List<NsUser> over_max_students = new ArrayList<>();

    private List<NsUser> session_full = new ArrayList<>();

    @BeforeEach
    void init() {
        IntStream.range(0, MAX_ENROLLMENT + 1)
                .forEach(index -> over_max_students.add(new NsUser()));

        IntStream.range(0, MAX_ENROLLMENT)
                .forEach(index -> session_full.add(new NsUser()));
    }

    @Test
    @DisplayName("수강신청 최대 인원을 초과하였을 경우 Exception 을 발생한다.")
    void overMaxTest() {
        assertThatThrownBy(() -> new Students(over_max_students))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 가능한 최대 인원을 초과하였습니다.");
    }

    @Test
    @DisplayName("수강생이 만석일 경우 한명의 학생을 더 추가할 경우 Exception 을 발생한다.")
    void fullAndAddOneTest() {
        assertThatThrownBy(() -> new Students(session_full).add(new NsUser()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 가능한 최대 인원을 초과하였습니다.");
    }
}
