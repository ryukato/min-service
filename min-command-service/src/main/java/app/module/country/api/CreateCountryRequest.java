package app.module.country.api;

import app.country.model.Country;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("unused")
public final class CreateCountryRequest extends CountryCommandRequest{

    @JsonCreator
    public CreateCountryRequest(Country country) {
        super(country);
    }

    @Override
    public String toString() {
        return "CreateCountryRequest{ " + super.toString() +"}";
    }
}
