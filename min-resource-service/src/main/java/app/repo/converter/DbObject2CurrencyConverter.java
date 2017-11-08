package app.repo.converter;

import app.module.currency.entity.CurrencyEntity;
import app.currency.model.Currency;
import com.mongodb.BasicDBObject;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DbObject2CurrencyConverter extends AuditEntityConverter implements Converter<BasicDBObject, CurrencyEntity> {
    private static final String ZERO = "0";
    @Override
    public CurrencyEntity convert(BasicDBObject source) {
        Currency currency = Currency.builder()
                .withCurrency(source.getString("currency"))
                .withCurrencyInKorean(source.getString("currencyInKorean"))
                .withBaseDateTime(LocalDateTime.ofInstant(source.getDate("baseDateTime").toInstant(), ZoneOffset.UTC))
                .withBuyInCashCurrency(toBigDecimal(source, "buyInCashCurrency"))
                .withBuyInCashSpread(toBigDecimal(source, "buyInCashSpread"))
                .withSellInCashCurrency(toBigDecimal(source, "sellInCashCurrency"))
                .withSellInCashSpread(toBigDecimal(source, "sellInCashSpread"))
                .withBuyInWireCurrency(toBigDecimal(source, "buyInWireCurrency"))
                .withSellInWireCurrency(toBigDecimal(source, "sellInWireCurrency"))
                .withTravelerCheckCurrency(toBigDecimal(source, "travelerCheckCurrency"))
                .withForeignCheckCurrency(toBigDecimal(source, "foreignCheckCurrency"))
                .withSellingBaseRate(toBigDecimal(source, "sellingBaseRate"))
                .withCurrencyInDollar(toBigDecimal(source, "currencyInDollar"))
                .build();
        CurrencyEntity currencyEntity = new CurrencyEntity(currency);
        currencyEntity.setId(source.getString("_id"));
        currencyEntity.setActive(source.getBoolean("active"));
        super.setAuditFields(currencyEntity, source);
        return currencyEntity;
    }

    private BigDecimal toBigDecimal(BasicDBObject source, String key) {
        return new BigDecimal((String) source.getOrDefault(key, ZERO));
    }
}
