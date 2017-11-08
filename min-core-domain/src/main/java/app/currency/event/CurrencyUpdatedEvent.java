package app.currency.event;

import app.currency.model.Currency;
import app.events.AbstractEvent;

public class CurrencyUpdatedEvent extends AbstractEvent {
    private final Currency currency;

    public CurrencyUpdatedEvent(String id, Currency currency) {
        super(id);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "CurrencyUpdatedEvent{" +
                "id=" + super.getId() +
                "currency=" + currency +
                "}";
    }
}
