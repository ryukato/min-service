package app.model;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}
