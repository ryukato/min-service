package app.module.country.api;

import app.module.country.entity.CountryEntity;
import app.country.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("unused")
@RestController
public class CountryResourceApiImpl implements CountryResourceApi {
    Country country = Country
            .builder()
            .withName("Korea")
            .withIsoCode("KR")
            .withCurrencies(Arrays.asList("KRW"))
            .build();
    CountryEntity countryEntity = new CountryEntity(country);

    public CountryResourceApiImpl() {
        countryEntity.setId("59aea4483d4a9e4199780dc3");
        countryEntity.setActive(true);
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public ResponseEntity<Page<CountryEntity>> findAll(Pageable pageable) {
        return ResponseEntity.ok(new PageImpl<>(Collections.singletonList(countryEntity)));
    }

    @Override
    public ResponseEntity<CountryEntity> findById(@PathVariable String id) {
        return ResponseEntity.ok(countryEntity);
    }

    @Override
    public ResponseEntity<CountryEntity> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(countryEntity);
    }

    @Override
    public ResponseEntity<Page<CountryEntity>> findByNameLike(@RequestParam("name") String name, Pageable pageable) {
        return ResponseEntity.ok(new PageImpl<>(Collections.singletonList(countryEntity)));
    }

    @Override
    public ResponseEntity<CountryEntity> findByIso(@RequestParam("iso") String iso) {
        return ResponseEntity.ok(countryEntity);
    }
}
