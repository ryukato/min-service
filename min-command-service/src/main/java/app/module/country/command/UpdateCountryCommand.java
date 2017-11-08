package app.module.country.command;

import app.country.model.Country;
import app.module.IdentifiableCommand;
import app.module.country.api.UpdateCountryRequest;
import org.springframework.util.Assert;

@SuppressWarnings("unused")
public final class UpdateCountryCommand extends IdentifiableCommand<String, String>{
    private final Country country;

    public UpdateCountryCommand(Country country) {
        super(country.getIsoCode());
        this.country = country;
    }

    @Override
    public String toString() {
        return "UpdateCountryCommand{" + super.toString() + "}";
    }

    public static UpdateCountryCommand newCommand(Country country) {
        return new UpdateCountryCommand(country);
    }

    public static UpdateCountryCommand from(UpdateCountryRequest request) {
        Assert.hasLength(request.getCountry().getIsoCode(), "Missing iso code");
        Assert.hasLength(request.getCountry().getName(), "Missing name");
        return new UpdateCountryCommand(request.getCountry());
    }

    public Country getCountry() {
        return country;
    }
}
