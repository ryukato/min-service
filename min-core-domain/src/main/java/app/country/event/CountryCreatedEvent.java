package app.country.event;

import app.country.model.Country;
import app.events.AbstractEvent;

@SuppressWarnings("unused")
public class CountryCreatedEvent extends AbstractEvent {
    private final Country country;
    public CountryCreatedEvent(Country country) {
        super(country.getIsoCode());
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "CountryCreatedEvent{ country=" + country + " }";
    }
}
