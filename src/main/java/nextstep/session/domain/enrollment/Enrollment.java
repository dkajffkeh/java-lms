package nextstep.session.domain.enrollment;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private static final String NOT_OPEN_MSG = "지금은 수강신청 가능 기간이 아닙니다.";

    private final Students students;

    private final SessionOpenPeriod sessionOpenPeriod;

    public Enrollment(Students students,
            SessionOpenPeriod sessionOpenPeriod) {
        this.students = students;
        this.sessionOpenPeriod = sessionOpenPeriod;
    }

    public void enrollSession(NsUser student) {
        if(!sessionOpenPeriod.isEnrollAvailable()) {
            throw new IllegalArgumentException(NOT_OPEN_MSG);
        }
        this.students.add(student);
    }
}
