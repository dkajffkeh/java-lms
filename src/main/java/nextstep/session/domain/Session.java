package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

public class Session {

    private static final String NOT_OPEN_MSG = "지금은 수강신청 가능 기간이 아닙니다.";

    private final Course course;

    private final SessionOpenPeriod sessionOpenPeriod;

    private final CoverImage coverImageUrl;

    private final PricingPlan pricingPlan;

    private final Students students;

    public Session(Course course, SessionOpenPeriod sessionOpenPeriod,
            CoverImage coverImageUrl, PricingPlan pricingPlan,
            Students students) {
        this.course = course;
        this.sessionOpenPeriod = sessionOpenPeriod;
        this.coverImageUrl = coverImageUrl;
        this.pricingPlan = pricingPlan;
        this.students = students;
    }

    public void enrollSession(NsUser student) {
        if(!sessionOpenPeriod.isEnrollAvailable()) {
            throw new IllegalArgumentException(NOT_OPEN_MSG);
        }
    }
}
