package app.module.currency.entity;

import app.currency.event.CurrencyCreatedEvent;
import app.currency.event.CurrencyUpdatedEvent;
import app.entity.AuditEntity;
import app.currency.model.Currency;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "currencies")
public class CurrencyEntity extends AuditEntity implements Identifiable, Serializable {
    @Id
    private String id;

    private final Currency currency;

    private boolean isActive;

    public CurrencyEntity(Currency currency) {
        this.currency = currency;
    }

    public CurrencyEntity(String id, Currency currency) {
        this.id = id;
        this.currency = currency;
    }

    @Override
    public String getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Field("active")
    public boolean isActive() {
        return this.isActive;
    }

    @Field("baseDateTime")
    public LocalDateTime baseDateTime() {
        return currency.getBaseDateTime();
    }

    @Field("currency")
    public String currency() {
        return currency.getCurrency();
    }

    @Field("currencyInKorean")
    public String currencyInKorean() {
        return currency.getCurrencyInKorean();
    }

    @Field("buyInCashCurrency")
    public BigDecimal buyInCashCurrency() {
        return currency.getBuyInCashCurrency();
    }

    @Field("buyInCashSpread")
    public BigDecimal buyInCashSpread() {
        return currency.getBuyInCashSpread();
    }

    @Field("sellInCashCurrency")
    public BigDecimal sellInCashCurrency() {
        return currency.getSellInCashCurrency();
    }

    @Field("sellInCashSpread")
    public BigDecimal sellInCashSpread() {
        return currency.getSellInCashSpread();
    }

    @Field("buyInWireCurrency")
    public BigDecimal buyInWireCurrency() {
        return currency.getBuyInWireCurrency();
    }

    @Field("sellInWireCurrency")
    public BigDecimal sellInWireCurrency() {
        return currency.getSellInWireCurrency();
    }

    @Field("travelerCheckCurrency")
    public BigDecimal travelerCheckCurrency() {
        return currency.getTravelerCheckCurrency();
    }

    @Field("foreignCheckCurrency")
    public BigDecimal foreignCheckCurrency() {
        return currency.getForeignCheckCurrency();
    }

    @Field("sellingBaseRate")
    public BigDecimal sellingBaseRate() {
        return currency.getSellingBaseRate();
    }

    @Field("currencyInDollar")
    public BigDecimal currencyInDollar() {
        return currency.getCurrencyInDollar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyEntity that = (CurrencyEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "CurrencyEntity{" +
                ", id='" + id + '\'' +
                "currency=" + currency + super.toString()+
                ", isActive=" + isActive +
                '}';
    }

    public static CurrencyEntity from(CurrencyCreatedEvent event) {
        return new CurrencyEntity(event.getId(), event.getCurrency());
    }

    public static CurrencyEntity from(CurrencyUpdatedEvent event) {
        return new CurrencyEntity(event.getId(), event.getCurrency());
    }
}
