package app.module.currency.api;

import app.currency.model.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateCurrencyRequest extends CurrencyCommandRequest {
    @JsonCreator
    public UpdateCurrencyRequest(Currency currency) {
        super(currency);
    }

    @Override
    public String toString() {
        return "UpdateCurrencyRequest{" + super.toString() + "}";
    }
}
