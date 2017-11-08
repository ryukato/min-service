package app.repo.converter;

import app.entity.AuditEntity;
import com.mongodb.BasicDBObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class AuditEntityConverter<T extends AuditEntity> {
    public Map<String, Object> getAuditFields(T source) {
        String createdBy = Optional.ofNullable(source.getCreatedBy()).orElse("SYSTEM");
        String lastModifiedBy = Optional.ofNullable(source.getModifiedBy()).orElse("SYSTEM");
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("createdAt", toDate(source.getCreatedAt()));
        map.put("createdBy", createdBy);
        map.put("lastModifiedAt", toDate(source.getLastModifiedAt()));
        map.put("lastModifiedBy", lastModifiedBy);
        return map;
    }

    public void setAuditFields(T t, BasicDBObject source) {
        t.setCreatedAt(LocalDateTime.ofInstant(source.getDate("createdAt").toInstant(), ZoneOffset.UTC));
        t.setLastModifiedAt(LocalDateTime.ofInstant(source.getDate("lastModifiedAt").toInstant(), ZoneOffset.UTC));
        t.setModifiedBy(source.getString("lastModifiedBy"));
        t.setCreatedBy(source.getString("createdBy"));
    }

    Date toDate(LocalDateTime localDateTime) {
        return Date.from(Optional.ofNullable(localDateTime).orElse(LocalDateTime.now()).toInstant(ZoneOffset.UTC));
    }
}
