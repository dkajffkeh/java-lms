package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;

import nextstep.common.entity.BaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {

    private static final String OTHER_USER_ANSWER_MSG = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private Question question;

    private NsUser writer;

    private String contents;

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super(id);
        if(writer == null) {
            throw new UnAuthorizedException();
        }
        if(question == null) {
            throw new NotFoundException();
        }
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return super.id();
    }

    public void deleteSelf(NsUser loginUser) {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException(OTHER_USER_ANSWER_MSG);
        }
        deleteEntity();
    }

    private boolean isOwner(NsUser loginUser) {
        return loginUser.equals(this.writer);
    }

    public DeleteHistory deleteHistory() {
        if(isEntityDeleted()) {
            return new DeleteHistory(ANSWER, super.id(), this.writer);
        }
        return new DeleteHistory();
    }

    public boolean isNotOwner(NsUser writer) {
        return !this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
