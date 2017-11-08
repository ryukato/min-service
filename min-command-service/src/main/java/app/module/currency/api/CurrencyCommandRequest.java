package app.module.currency.api;

import app.currency.model.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("unused")
public class CurrencyCommandRequest {
    private final Currency currency;

    @JsonCreator
    public CurrencyCommandRequest(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "currency=" + currency;
    }
}
