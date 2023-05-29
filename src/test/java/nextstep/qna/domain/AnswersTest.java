package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nextstep.dummy.answer.AnswerDummy;
import nextstep.dummy.answer.NsUserDummy;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    private Answer a_user_answer;

    private NsUser a_user;

    private Answers singleAnswer;

    private NsUser b_user;

    @BeforeEach
    void init() {
        a_user_answer = new AnswerDummy().a_answer;
        a_user = new NsUserDummy().a_user;
        b_user = new NsUserDummy().b_user;
        singleAnswer = new Answers(List.of(a_user_answer));
    }

    @Test
    @DisplayName("질문 목록중 로그인 유저와 다른 유저가 배열에 존재할 경우 true 를 리턴한다.")
    void checkHasOtherUser() {
        assertThat(singleAnswer.hasOtherUserAnswer(b_user))
                .isTrue();
    }

    @Test
    @DisplayName("질문 전체를 삭제했을 경우 Answer 의 삭제값이 전체 True 로 상태값이 변경된다.")
    void deleteAllTest() {
        singleAnswer.deleteAll(a_user);

        final Optional<Answer> notDeletedAnswer = singleAnswer.immutableGet()
                .stream()
                .filter(answer -> !answer.isEntityDeleted())
                .findFirst();

        assertThat(notDeletedAnswer.isEmpty())
                .isTrue();
    }

    @Test
    @DisplayName("삭제되지 않은 답변에 대해 서는 비어있는 내역을 반환한다.")
    void historiesNotYetDeletedTest() {
        assertThat(singleAnswer.deleteHistories().isEmpty())
                .isTrue();
    }

    @Test
    @DisplayName("답변에 대해 삭제 내역을 리턴한다.")
    void deleteHistoriesTest() {
        singleAnswer.deleteAll(a_user);
        assertThat(singleAnswer.deleteHistories())
                .isEqualTo(new ArrayList<>(List.of(
                        new DeleteHistory(ANSWER,null, a_user)
                )));
    }
}
