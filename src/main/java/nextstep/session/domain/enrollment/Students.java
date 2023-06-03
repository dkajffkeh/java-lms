package nextstep.session.domain.enrollment;

import java.util.List;
import nextstep.users.domain.NsUser;

public class Students {

    private static final String OVER_MAX = "수강 가능한 최대 인원을 초과하였습니다.";

    private final Capacity capacity;

    private final List<NsUser> students;

    public Students(List<NsUser> students, int capacity) {
        this(students, new Capacity(capacity));
    }

    public Students(List<NsUser> students, Capacity capacity) {
        if(capacity.isFull(students.size())) {
            throw new IllegalArgumentException(OVER_MAX);
        }
        this.capacity = capacity;
        this.students = students;
    }

    public int size() {
        return this.students.size();
    }

    public Students add(NsUser student) {
        this.students.add(student);
        return new Students(this.students, this.capacity);
    }
}
