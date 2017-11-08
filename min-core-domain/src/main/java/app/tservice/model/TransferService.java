package app.tservice.model;

import app.codes.model.ApplicationType;
import app.codes.model.ReceiveMethod;
import app.codes.model.ServiceMethod;
import app.codes.model.TransferMethod;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
public class TransferService implements Serializable {
    private final String name;
    private final String feeCurrencyCode;
    private final Collection<TransferFee> transferFees;
    private final Collection<TransferMethod> transferMethods;
    private final Collection<ServiceMethod> serviceMethods;
    private final Collection<ApplicationType> applicationTypes;
    private final Collection<String> countries;
    private final Collection<String> currencies;
    private final Collection<ReceiveMethod> receiveMethods;

    private TransferService(Builder builder) {
        name = builder.name;
        feeCurrencyCode = builder.feeCurrencyCode;
        transferFees = builder.transferFees;
        transferMethods = builder.transferMethods;
        serviceMethods = builder.serviceMethods;
        applicationTypes = builder.applicationTypes;
        countries = builder.countries;
        currencies = builder.currencies;
        receiveMethods = builder.receiveMethods;
    }

    public String getName() {
        return name;
    }

    public String getFeeCurrencyCode() {
        return feeCurrencyCode;
    }

    public Collection<TransferFee> getTransferFees() {
        return transferFees;
    }

    public Collection<TransferMethod> getTransferMethods() {
        return transferMethods;
    }

    public Collection<ServiceMethod> getServiceMethods() {
        return serviceMethods;
    }

    public Collection<ApplicationType> getApplicationTypes() {
        return applicationTypes;
    }

    public Collection<String> getCountries() {
        return countries;
    }

    public Collection<String> getCurrencies() {
        return currencies;
    }

    public Collection<ReceiveMethod> getReceiveMethods() {
        return receiveMethods;
    }

    @Override
    public String toString() {
        return "TransferService{" +
                "name='" + name + '\'' +
                ", feeCurrencyCode='" + feeCurrencyCode + '\'' +
                ", transferFees=" + transferFees +
                ", transferMethods=" + transferMethods +
                ", serviceMethods=" + serviceMethods +
                ", applicationTypes=" + applicationTypes +
                ", countries=" + countries +
                ", currencies=" + currencies +
                ", receiveMethods=" + receiveMethods +
                '}';
    }

    public static class Builder {
        private String name;
        private String feeCurrencyCode;
        private Collection<TransferFee> transferFees;
        private Collection<TransferMethod> transferMethods;
        private Collection<ServiceMethod> serviceMethods;
        private Collection<ApplicationType> applicationTypes;
        private Collection<String> countries;
        private Collection<String> currencies;
        private Collection<ReceiveMethod> receiveMethods;

        public Builder withName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Empty name is not allowed");
            }
            this.name = name; return this;
        }

        public Builder withFeeCurrencyCode(String feeCurrencyCode) {
            if (feeCurrencyCode == null || feeCurrencyCode.isEmpty()) {
                throw new IllegalArgumentException("Empty FeeCurrencyCode is not allowed");
            }
            this.feeCurrencyCode = feeCurrencyCode; return this;
        }

        public Builder withTransferFees(Collection<TransferFee> transferFees) {
            if (transferFees == null || transferFees.isEmpty()) {
                throw new IllegalArgumentException("Empty transferFees is not allowed");
            }
            this.transferFees = transferFees; return this;
        }

        public Builder withTransferMethods(Collection<TransferMethod> transferMethods) {
            if (transferMethods == null || transferMethods.isEmpty()) {
                throw new IllegalArgumentException("Empty transferMethods is not allowed");
            }
            this.transferMethods = transferMethods; return this;
        }

        public Builder withServiceMethods(Collection<ServiceMethod> serviceMethods) {
            if (serviceMethods == null || serviceMethods.isEmpty()) {
                throw new IllegalArgumentException("Empty serviceMethods is not allowed");
            }
            this.serviceMethods = serviceMethods; return this;
        }

        public Builder withApplicationTypes(Collection<ApplicationType> applicationTypes) {
            if (applicationTypes == null || applicationTypes.isEmpty()) {
                throw new IllegalArgumentException("Empty applicationTypes is not allowed");
            }
            this.applicationTypes = applicationTypes; return this;
        }

        public Builder withCountries(Collection<String> countries) {
            if (countries == null || countries.isEmpty()) {
                throw new IllegalArgumentException("Empty countries is not allowed");
            }
            this.countries = countries; return this;
        }

        public Builder withCurrencies(Collection<String> currencies) {
            if (currencies == null || currencies.isEmpty()) {
                throw new IllegalArgumentException("Empty currencies is not allowed");
            }
            this.currencies = currencies; return this;
        }

        public Builder withReceiveMethods(Collection<ReceiveMethod> receiveMethods) {
            if (receiveMethods == null || receiveMethods.isEmpty()) {
                throw new IllegalArgumentException("Empty receiveMethods is not allowed");
            }
            this.receiveMethods = receiveMethods; return this;
        }

        public TransferService build() {
            return new TransferService(this);
        }
    }
}
