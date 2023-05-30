package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.common.entity.BaseEntity;

public class Course extends BaseEntity {
    private String title;

    private Long creatorId;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedDate();
    }

}
