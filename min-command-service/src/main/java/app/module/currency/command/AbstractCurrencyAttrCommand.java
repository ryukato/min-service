package app.module.currency.command;

import app.currency.model.Currency;
import app.module.IdentifiableCommand;

public abstract class AbstractCurrencyAttrCommand extends IdentifiableCommand<String, String> {
    private final Currency currency;

    protected AbstractCurrencyAttrCommand(String id, Currency currency) {
        super(id);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "currency=" + currency +
                '}';
    }
}
