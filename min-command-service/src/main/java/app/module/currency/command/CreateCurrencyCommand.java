package app.module.currency.command;

import app.currency.model.Currency;
import app.module.currency.api.CreateCurrencyRequest;

public final class CreateCurrencyCommand extends AbstractCurrencyAttrCommand {

    public CreateCurrencyCommand(String id, Currency currency) {
        super(id, currency);
    }

    public static CreateCurrencyCommand from(String id, CreateCurrencyRequest request) {
        return new CreateCurrencyCommand(id, request.getCurrency());
    }

    public static CreateCurrencyCommand newCommand(String id, Currency currency) {
        return new CreateCurrencyCommand(id, currency);
    }
}
