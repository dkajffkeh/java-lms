package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Students {

    protected static final int MAX_ENROLLMENT = 10;

    private static final String OVER_MAX = "수강 가능한 최대 인원을 초과하였습니다.";

    private final List<NsUser> students;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<NsUser> students) {
        if(isMax(students)) {
            throw new IllegalArgumentException(OVER_MAX);
        }
        this.students = students;
    }

    private boolean isMax(List<NsUser> students) {
        return students.size() > MAX_ENROLLMENT;
    }

    public int size() {
        return this.students.size();
    }

    public Students add(NsUser student) {
        this.students.add(student);
        return new Students(this.students);
    }
}
