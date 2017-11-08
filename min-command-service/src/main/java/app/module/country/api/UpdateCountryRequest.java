package app.module.country.api;

import app.country.model.Country;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("unused")
public final class UpdateCountryRequest extends CountryCommandRequest{

    @JsonCreator
    public UpdateCountryRequest(Country country) {
        super(country);
    }

    @Override
    public String toString() {
        return "UpdateCountryRequest{ " + super.toString() +"}";
    }
}
