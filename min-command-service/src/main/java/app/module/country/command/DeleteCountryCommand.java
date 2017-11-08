package app.module.country.command;

import app.module.IdentifiableCommand;
import org.springframework.util.Assert;

@SuppressWarnings("unused")
public final class DeleteCountryCommand extends IdentifiableCommand<String, String> {
    private DeleteCountryCommand(String id) {
        super(id);
        Assert.hasLength(id, "Missing iso code");
    }


    public String getIsoCode() {
        return super.getId();
    }

    public static DeleteCountryCommand from(String isoCode) {
        Assert.hasLength(isoCode, "Missing iso code");
        return new DeleteCountryCommand(isoCode);
    }

    @Override
    public String toString() {
        return "DeleteCountryCommand{" + super.identifier() + "}";
    }
}
