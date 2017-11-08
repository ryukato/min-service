package app.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class AuditEntity {
    @CreatedBy
    private String createdBy = "SYSTEM";

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedBy
    private String modifiedBy = "SYSTEM";

    @LastModifiedDate
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public String toString() {
        return "" +
                "createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", lastModifiedAt=" + lastModifiedAt ;
    }
}
