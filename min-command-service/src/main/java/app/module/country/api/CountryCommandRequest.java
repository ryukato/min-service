package app.module.country.api;

import app.country.model.Country;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("unused")
public class CountryCommandRequest implements DescriptableRequest{
    private  Country country;

    public CountryCommandRequest() {
    }

    @JsonCreator
    public CountryCommandRequest(Country country) {
        this.country = country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "country=" + country ;
    }

    @Override
    public String description() {
        return toString();
    }
}

