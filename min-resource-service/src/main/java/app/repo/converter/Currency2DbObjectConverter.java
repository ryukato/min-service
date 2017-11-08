package app.repo.converter;

import app.module.currency.entity.CurrencyEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class Currency2DbObjectConverter extends AuditEntityConverter implements Converter<CurrencyEntity, DBObject> {
    private static final String ZERO = "0";
    @Override
    public DBObject convert(CurrencyEntity source) {
        Map<String, Object> map = super.getAuditFields(source);
        map.put("currency",source.currency());
        map.put("currencyInKorean",source.currencyInKorean());
        map.put("baseDateTime",toDate(source.baseDateTime()));
        map.put("buyInCashCurrency", toPlainString(source.buyInCashCurrency()));
        map.put("buyInCashSpread", toPlainString(source.buyInCashSpread()));
        map.put("sellInCashCurrency", toPlainString(source.sellInCashCurrency()));
        map.put("sellInCashSpread", toPlainString(source.sellInCashSpread()));
        map.put("buyInWireCurrency", toPlainString(source.buyInWireCurrency()));
        map.put("sellInWireCurrency", toPlainString(source.sellInWireCurrency()));
        map.put("travelerCheckCurrency", toPlainString(source.travelerCheckCurrency()));
        map.put("foreignCheckCurrency", toPlainString(source.foreignCheckCurrency()));
        map.put("sellingBaseRate", toPlainString(source.sellingBaseRate()));
        map.put("currencyInDollar", toPlainString(source.currencyInDollar()));
        return new BasicDBObject(map);
    }

    private String toPlainString(BigDecimal bigDecimal) {
        return Optional.ofNullable(bigDecimal).map(b -> b.toPlainString()).orElse(ZERO);
    }
}
