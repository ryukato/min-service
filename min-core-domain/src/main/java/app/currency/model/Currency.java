package app.currency.model;

import app.model.InvalidCurrencyException;
import app.util.LocalDateTimeUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@SuppressWarnings("unused")
@JsonDeserialize(builder = Currency.Builder.class)
public class Currency {
    private final LocalDateTime baseDateTime;
    private final String currency;
    private final String currencyInKorean;
    private final BigDecimal buyInCashCurrency;
    private final BigDecimal buyInCashSpread;
    private final BigDecimal sellInCashCurrency;
    private final BigDecimal sellInCashSpread;
    private final BigDecimal buyInWireCurrency;
    private final BigDecimal sellInWireCurrency;
    private final BigDecimal travelerCheckCurrency;
    private final BigDecimal foreignCheckCurrency;
    private final BigDecimal sellingBaseRate;
    private final BigDecimal currencyInDollar;

    private Currency(
            LocalDateTime baseDateTime,
            String currency,
            String currencyInKorean,
            BigDecimal buyInCashCurrency,
            BigDecimal buyInCashSpread,
            BigDecimal sellInCashCurrency,
            BigDecimal sellInCashSpread,
            BigDecimal buyInWireCurrency,
            BigDecimal sellInWireCurrency,
            BigDecimal travelerCheckCurrency,
            BigDecimal foreignCheckCurrency,
            BigDecimal sellingBaseRate,
            BigDecimal currencyInDollar) {
        this.baseDateTime = baseDateTime;
        this.currency = currency;
        this.currencyInKorean = currencyInKorean;
        this.buyInCashCurrency = buyInCashCurrency;
        this.buyInCashSpread = buyInCashSpread;
        this.sellInCashCurrency = sellInCashCurrency;
        this.sellInCashSpread = sellInCashSpread;
        this.buyInWireCurrency = buyInWireCurrency;
        this.sellInWireCurrency = sellInWireCurrency;
        this.travelerCheckCurrency = travelerCheckCurrency;
        this.foreignCheckCurrency = foreignCheckCurrency;
        this.sellingBaseRate = sellingBaseRate;
        this.currencyInDollar = currencyInDollar;
    }

    public LocalDateTime getBaseDateTime() {
        return baseDateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyInKorean() {
        return currencyInKorean;
    }

    public BigDecimal getBuyInCashCurrency() {
        return buyInCashCurrency;
    }

    public BigDecimal getBuyInCashSpread() {
        return buyInCashSpread;
    }

    public BigDecimal getSellInCashCurrency() {
        return sellInCashCurrency;
    }

    public BigDecimal getSellInCashSpread() {
        return sellInCashSpread;
    }

    public BigDecimal getBuyInWireCurrency() {
        return buyInWireCurrency;
    }

    public BigDecimal getSellInWireCurrency() {
        return sellInWireCurrency;
    }

    public BigDecimal getTravelerCheckCurrency() {
        return travelerCheckCurrency;
    }

    public BigDecimal getForeignCheckCurrency() {
        return foreignCheckCurrency;
    }

    public BigDecimal getSellingBaseRate() {
        return sellingBaseRate;
    }

    public BigDecimal getCurrencyInDollar() {
        return currencyInDollar;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "baseDateTime='" + baseDateTime + '\'' +
                "currency='" + currency + '\'' +
                ", currencyInKorean='" + currencyInKorean + '\'' +
                ", buyInCashCurrency=" + buyInCashCurrency +
                ", buyInCashSpread=" + buyInCashSpread +
                ", sellInCashCurrency=" + sellInCashCurrency +
                ", sellInCashSpread=" + sellInCashSpread +
                ", buyInWireCurrency=" + buyInWireCurrency +
                ", sellInWireCurrency=" + sellInWireCurrency +
                ", travelerCheckCurrency=" + travelerCheckCurrency +
                ", foreignCheckCurrency=" + foreignCheckCurrency +
                ", sellingBaseRate=" + sellingBaseRate +
                ", currencyInDollar=" + currencyInDollar +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private LocalDateTime baseDateTime;
        private String currency;
        private String currencyInKorean;
        private BigDecimal buyInCashCurrency;
        private BigDecimal buyInCashSpread;
        private BigDecimal sellInCashCurrency;
        private BigDecimal sellInCashSpread;
        private BigDecimal buyInWireCurrency;
        private BigDecimal sellInWireCurrency;
        private BigDecimal travelerCheckCurrency;
        private BigDecimal foreignCheckCurrency;
        private BigDecimal sellingBaseRate;
        private BigDecimal currencyInDollar;

        public Builder withBaseDateTime(String baseDateTime) {
            return this.withBaseDateTime(baseDateTime, "yyyMMdd HHmmss");
        }

        public Builder withBaseDateTime(String baseDateTime, String format) {
            this.baseDateTime = LocalDateTimeUtil.from(baseDateTime, format);
            return this;
        }

        public Builder withBaseDateTime(LocalDateTime baseDateTime) {
            this.baseDateTime = baseDateTime;
            return this;
        }

        public Builder withCurrency(String currency) {
            if (currency == null || currency.isEmpty()) {
                throw new InvalidCurrencyException("Empty currency");
            }
            this.currency = currency;
            return this;
        }

        public Builder withCurrencyInKorean(String currencyInKorean) {
            if (currencyInKorean == null || currencyInKorean.isEmpty()) {
                throw new InvalidCurrencyException("Empty currency");
            }
            this.currencyInKorean = currencyInKorean;
            return this;
        }

        private BigDecimal defaultCurrencyValueIfEmpty(BigDecimal input) {
            return Optional.ofNullable(input).orElse(new BigDecimal("0"));
        }

        public Builder withBuyInCashCurrency(BigDecimal buyInCashCurrency) {
            this.buyInCashCurrency = defaultCurrencyValueIfEmpty(buyInCashCurrency);
            return this;
        }

        public Builder withBuyInCashSpread(BigDecimal buyInCashSpread) {
            this.buyInCashSpread = defaultCurrencyValueIfEmpty(buyInCashSpread);
            return this;
        }

        public Builder withSellInCashCurrency(BigDecimal sellInCashCurrency) {
            this.sellInCashCurrency = defaultCurrencyValueIfEmpty(sellInCashCurrency);
            return this;
        }

        public Builder withSellInCashSpread(BigDecimal sellInCashSpread) {
            this.sellInCashSpread = defaultCurrencyValueIfEmpty(sellInCashSpread);
            return this;
        }

        public Builder withBuyInWireCurrency(BigDecimal buyInWireCurrency) {
            this.buyInWireCurrency = defaultCurrencyValueIfEmpty(buyInWireCurrency);
            return this;
        }

        public Builder withSellInWireCurrency(BigDecimal sellInWireCurrency) {
            this.sellInWireCurrency = defaultCurrencyValueIfEmpty(sellInWireCurrency);
            return this;
        }

        public Builder withTravelerCheckCurrency(BigDecimal travelerCheckCurrency) {
            this.travelerCheckCurrency = defaultCurrencyValueIfEmpty(travelerCheckCurrency);
            return this;
        }

        public Builder withForeignCheckCurrency(BigDecimal foreignCheckCurrency) {
            this.foreignCheckCurrency = defaultCurrencyValueIfEmpty(foreignCheckCurrency);
            return this;
        }

        public Builder withSellingBaseRate(BigDecimal sellingBaseRate) {
            this.sellingBaseRate = defaultCurrencyValueIfEmpty(sellingBaseRate);
            return this;
        }

        public Builder withCurrencyInDollar(BigDecimal currencyInDollar) {
            this.currencyInDollar = defaultCurrencyValueIfEmpty(currencyInDollar);
            return this;
        }

        public Currency build() {
            return new Currency(
                    baseDateTime,
                    currency,
                    currencyInKorean,
                    buyInCashCurrency,
                    buyInCashSpread,
                    sellInCashCurrency,
                    sellInCashSpread,
                    buyInWireCurrency,
                    sellInWireCurrency,
                    travelerCheckCurrency,
                    foreignCheckCurrency,
                    sellingBaseRate,
                    currencyInDollar);
        }
    }
}