package app.module.currency.api;

import app.currency.model.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("unused")
public class CreateCurrencyRequest extends CurrencyCommandRequest {

    @JsonCreator
    public CreateCurrencyRequest(Currency currency) {
        super(currency);
    }

    @Override
    public String toString() {
        return "CreateCurrencyRequest{" + super.toString() + "}";
    }
}
