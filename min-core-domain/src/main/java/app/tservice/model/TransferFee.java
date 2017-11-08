package app.tservice.model;

import app.codes.model.ReceiveMethod;
import app.codes.model.TransferFeeType;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferFee implements Serializable {

    private final ReceiveMethod receiveMethod;
    private final String currency;
    private final BigDecimal fromAmount;
    private final BigDecimal toAmount;
    private final BigDecimal fee;
    private final TransferFeeType type;

    private TransferFee(Builder builder) {
        this.receiveMethod = builder.receiveMethod;
        this.currency = builder.currency;
        this.fromAmount = builder.fromAmount;
        this.toAmount = builder.toAmount;
        this.fee = builder.fee;
        this.type = builder.type;
    }

    public ReceiveMethod getReceiveMethod() {
        return receiveMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public TransferFeeType getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferFee that = (TransferFee) o;

        if (receiveMethod != that.receiveMethod) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (fromAmount != null ? !fromAmount.equals(that.fromAmount) : that.fromAmount != null) return false;
        if (toAmount != null ? !toAmount.equals(that.toAmount) : that.toAmount != null) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = receiveMethod != null ? receiveMethod.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (fromAmount != null ? fromAmount.hashCode() : 0);
        result = 31 * result + (toAmount != null ? toAmount.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransferFee{" +
                "receiveMethod=" + receiveMethod +
                ", currency='" + currency + '\'' +
                ", fromAmount=" + fromAmount +
                ", toAmount=" + toAmount +
                ", fee=" + fee +
                ", type=" + type +
                '}';
    }

    public static class Builder {
        private ReceiveMethod receiveMethod;
        private String currency;
        private BigDecimal fromAmount;
        private BigDecimal toAmount;
        private BigDecimal fee;
        private TransferFeeType type;

        public Builder withReceiveMethod(ReceiveMethod receiveMethod) {
            this.receiveMethod = receiveMethod;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withFromAmount(BigDecimal fromAmount) {
            this.fromAmount = fromAmount;
            return this;
        }

        public Builder withToAmount(BigDecimal toAmount) {
            this.toAmount = toAmount;
            return this;
        }

        public Builder withFee(BigDecimal fee) {
            this.fee = fee;
            return this;
        }

        public Builder withType(TransferFeeType type) {
            this.type = type;
            return this;
        }

        public TransferFee build() {
            return new TransferFee(this);
        }
    }
}
