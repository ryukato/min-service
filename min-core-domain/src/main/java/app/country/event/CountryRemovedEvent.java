package app.country.event;

import app.events.AbstractEvent;

@SuppressWarnings("unused")
public class CountryRemovedEvent extends AbstractEvent {

    public CountryRemovedEvent(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "CountryRemovedEvent{" +super.getId()+ "}";
    }
}
