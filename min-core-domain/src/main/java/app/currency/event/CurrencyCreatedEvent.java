package app.currency.event;

import app.currency.model.Currency;
import app.events.AbstractEvent;

public class CurrencyCreatedEvent extends AbstractEvent {
    private final Currency currency;

    public CurrencyCreatedEvent(String id, Currency currency) {
        super(id);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "CurrencyCreatedEvent{ currency=" + currency + " }";
    }
}
