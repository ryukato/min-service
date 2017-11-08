package app.country.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@JsonDeserialize(builder = Country.Builder.class)
public class Country implements Serializable{
    private final String name;
    private final String isoCode;
    private final List<String> currencies;

    private Country(String isoCode, String name, List<String> currencies) {
        this.name = name;
        this.isoCode = isoCode;
        this.currencies = Collections.unmodifiableList(currencies);
    }

    private Country(Builder builder) {
        this(builder.isoCode, builder.name, builder.currencies);
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", currencies=" + currencies +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String isoCode;
        private boolean isActive;
        private List<String> currencies = Collections.emptyList();
        private Map<String, Object> country = new HashMap<>();

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIsoCode(String isoCode) {
            this.isoCode = isoCode;
            return this;
        }

        public Builder withCurrencies(List<String> currencies) {
            this.currencies = currencies;
            return this;
        }

        public Builder withCountry(Map<String, Object> country) {
            this.isoCode = (String) country.get("isoCode");
            this.name = (String) country.get("name");
            Assert.hasLength(isoCode, "Not allowed empty isoCode");
            Assert.hasLength(name, "Not allowed empty name");
            this.currencies = (List<String>) country.get("currencies");
            return this;
        }

        public Country build() {
            return new Country(this);
        }

        public Country from(Country country) {
            return new Country(country.getIsoCode(), country.getName(), country.currencies);
        }

    }
}
