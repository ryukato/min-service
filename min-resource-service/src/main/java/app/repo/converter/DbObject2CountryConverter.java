package app.repo.converter;

import app.module.country.entity.CountryEntity;
import app.country.model.Country;
import com.mongodb.BasicDBObject;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.List;

public class DbObject2CountryConverter extends AuditEntityConverter implements Converter<BasicDBObject, CountryEntity> {
    @Override
    public CountryEntity convert(BasicDBObject source) {
        Country country = Country.builder()
                .withName(source.getString("name"))
                .withIsoCode(source.getString("iso"))
                .withCurrencies((List<String>) source.getOrDefault("currencies", Collections.emptyList()))
                .build();
        CountryEntity countryEntity = new CountryEntity(country);
        countryEntity.setActive(source.getBoolean("active"));
        super.setAuditFields(countryEntity, source);
        countryEntity.setId(source.getString("_id"));
        return countryEntity;
    }
}
