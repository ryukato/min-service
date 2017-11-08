package app.module.currency.command;

import app.currency.model.Currency;
import app.module.currency.api.UpdateCurrencyRequest;

public final class UpdateCurrencyCommand extends AbstractCurrencyAttrCommand {
    public UpdateCurrencyCommand(String id, Currency currency) {
        super(id, currency);
    }

    public static UpdateCurrencyCommand from(String id, UpdateCurrencyRequest request) {
        return new UpdateCurrencyCommand(id, request.getCurrency());
    }

    public static UpdateCurrencyCommand newCommand(String id, Currency currency) {
        return new UpdateCurrencyCommand(id, currency);
    }
}
