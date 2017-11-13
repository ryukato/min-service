package app.error;

public class ResourceEntityNotFoundException extends RuntimeException {
    public ResourceEntityNotFoundException(String message) {
        super(message);
    }
}
