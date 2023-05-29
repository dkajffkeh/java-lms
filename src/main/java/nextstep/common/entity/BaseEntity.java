package nextstep.common.entity;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

public class BaseEntity {

    private Long id;

    private boolean deleted = false;

    private LocalDateTime createdDate = now();

    private LocalDateTime updatedDate;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long id() {
        return this.id;
    }

    public boolean isEntityDeleted() {
        return deleted;
    }

    public void deleteEntity() {
        this.deleted = true;
    }
}
