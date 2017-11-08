package app.module.currency.command;

import app.module.IdentifiableCommand;
import org.springframework.util.Assert;

public class DeleteCurrencyCommand extends IdentifiableCommand<String, String> {
    private DeleteCurrencyCommand(String id) {
        super(id);
    }

    public static DeleteCurrencyCommand from(String id) {
        Assert.hasLength(id, "Missing id");
        return new DeleteCurrencyCommand(id);
    }

    @Override
    public String toString() {
        return "DeleteCurrencyCommand{" + super.identifier() + "}";
    }
}
