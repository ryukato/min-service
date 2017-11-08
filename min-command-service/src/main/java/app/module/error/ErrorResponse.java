package app.module.error;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private final String message;
    private final String exceptionType;
    private final String path;
    private final HttpStatus status;
    private final Long timestamp;

    public ErrorResponse(Throwable t, String message, String path, HttpStatus httpStatus) {
        this.message = message;
        this.exceptionType = t.getClass().getSimpleName();
        this.path = path;
        this.status = httpStatus;
        this.timestamp = System.currentTimeMillis();
    }

    public ErrorResponse(Throwable t, String path, HttpStatus httpStatus) {
        this.message = t.getMessage();
        this.exceptionType = t.getClass().getSimpleName();
        this.path = path;
        this.status = httpStatus;
        this.timestamp = System.currentTimeMillis();
    }

    public static ErrorResponse badRequest(Throwable t, String message, String path) {
        return new ErrorResponse(t, message, path, HttpStatus.BAD_REQUEST);
    }

    public static ErrorResponse badRequest(Throwable t, String path) {
        return new ErrorResponse(t, path, HttpStatus.BAD_REQUEST);
    }

    public static ErrorResponse conflict(Throwable t, String path) {
        return new ErrorResponse(t, path, HttpStatus.CONFLICT);
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}