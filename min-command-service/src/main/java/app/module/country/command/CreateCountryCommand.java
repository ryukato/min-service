package app.module.country.command;

import app.country.model.Country;
import app.module.IdentifiableCommand;
import app.module.country.api.CreateCountryRequest;
import org.springframework.util.Assert;

@SuppressWarnings("unused")
public final class CreateCountryCommand extends IdentifiableCommand<String, String> {
    private final Country country;

    public CreateCountryCommand(Country country) {
        super(country.getIsoCode());
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "CreateCountryCommand{" + super.toString() + "}";
    }

    public static CreateCountryCommand newCommand(Country country) {
        return new CreateCountryCommand(country);
    }

    public static CreateCountryCommand from(CreateCountryRequest request) {
        Assert.hasLength(request.getCountry().getIsoCode(), "Missing iso code");
        Assert.hasLength(request.getCountry().getName(), "Missing name");
        return new CreateCountryCommand(request.getCountry());
    }
}
