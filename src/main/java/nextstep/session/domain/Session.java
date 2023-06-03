package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.session.domain.enrollment.Enrollment;
import nextstep.session.domain.enrollment.SessionOpenPeriod;
import nextstep.session.domain.enrollment.Students;
import nextstep.users.domain.NsUser;

public class Session {

    private final Course course;

    private final CoverImage coverImage;

    private final PricingPlan pricingPlan;

    private final Enrollment enrollment;

    public Session(Course course, SessionOpenPeriod sessionOpenPeriod,
            CoverImage coverImage, PricingPlan pricingPlan,
            Students students) {
        this(course, coverImage, pricingPlan, new Enrollment(students, sessionOpenPeriod));
    }

    public Session(Course course, CoverImage coverImage,
            PricingPlan pricingPlan, Enrollment enrollment) {
        this.course = course;
        this.coverImage = coverImage;
        this.pricingPlan = pricingPlan;
        this.enrollment = enrollment;
    }

    public void enrollSession(NsUser student) {
        this.enrollment.enrollSession(student);
    }
}
