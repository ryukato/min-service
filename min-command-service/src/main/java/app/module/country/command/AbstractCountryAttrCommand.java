package app.module.country.command;

import app.module.IdentifiableCommand;

import java.util.List;

public class AbstractCountryAttrCommand extends IdentifiableCommand<String, String> {
    private final String countryName;
    private final String isoCode;
    private final List<String> currencies;

    public AbstractCountryAttrCommand(String isoCode, String countryName, List<String> currencies) {
        super(isoCode);
        this.countryName = countryName;
        this.isoCode = isoCode;
        this.currencies = currencies;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    @Override
    public String toString() {
        return "countryName='" + countryName + "\'" +
               ", isoCode='" + isoCode + "\'" +
               ", currencies=" + currencies;
    }
}
