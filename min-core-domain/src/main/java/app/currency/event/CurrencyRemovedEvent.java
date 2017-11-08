package app.currency.event;

import app.events.AbstractEvent;

@SuppressWarnings("unused")
public class CurrencyRemovedEvent extends AbstractEvent {

    public CurrencyRemovedEvent(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "CurrencyRemovedEvent{" +super.getId()+ "}";
    }
}