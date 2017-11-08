package app.repo.converter;

import app.module.country.entity.CountryEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

public class Country2DbObjectConverter extends AuditEntityConverter implements Converter<CountryEntity, DBObject> {
    @Override
    public BasicDBObject convert(CountryEntity source) {

        Map<String, Object> map = super.getAuditFields(source);
        map.put("name", source.name());
        map.put("iso", source.iso());
        map.put("active", source.isActive());
        map.put("currencies", source.currencies());
        return new BasicDBObject(map);
    }
}
