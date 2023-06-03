package nextstep.session.domain.enrollment;

public class Capacity {

    private final int capacity;

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull(int numberOfStudents) {
        return numberOfStudents > this.capacity;
    }

}
