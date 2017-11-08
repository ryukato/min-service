package app.module.currency.api;

import app.module.currency.entity.CurrencyEntity;
import app.currency.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
public class CurrencyResourceApiImpl implements CurrencyResourceApi {
    BigDecimal sampleCurrency = new BigDecimal("1.111");
    Currency currency = Currency.builder()
            .withBaseDateTime(LocalDateTime.now())
            .withCurrency("USD")
            .withCurrencyInKorean("달러")
            .withBuyInCashCurrency(sampleCurrency)
            .withBuyInCashSpread(sampleCurrency)
            .withSellInCashCurrency(sampleCurrency)
            .withSellInCashSpread(sampleCurrency)
            .withSellInWireCurrency(sampleCurrency)
            .withTravelerCheckCurrency(sampleCurrency)
            .withForeignCheckCurrency(sampleCurrency)
            .withSellingBaseRate(sampleCurrency)
            .withCurrencyInDollar(sampleCurrency)
            .build();
    CurrencyEntity currencyEntity = new CurrencyEntity(currency);

    public CurrencyResourceApiImpl() {
        currencyEntity.setId(UUID.randomUUID().toString());
        currencyEntity.setActive(true);
    }

    @Override
    public ResponseEntity<Page<CurrencyEntity>> findAll(Pageable pageable) {
        return ResponseEntity.ok(new PageImpl<>(Collections.singletonList(currencyEntity)));
    }

    @Override
    public ResponseEntity<CurrencyEntity> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(currencyEntity);
    }

    @Override
    public ResponseEntity<CurrencyEntity> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(currencyEntity);
    }

    @Override
    public ResponseEntity<CurrencyEntity> findByKoreanName(@RequestParam("name") String name) {
        return ResponseEntity.ok(currencyEntity);
    }
}
