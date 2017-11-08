package app.module.error;

import app.util.RequestParamUtil;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@SuppressWarnings("unused")
@ControllerAdvice
public class ExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler({AssertionError.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> badRequest(HttpServletRequest request, RuntimeException e) {
        logger.warn("Request api call failed. URL: {}, params: {}, error: {}", request.getRequestURL(), request.getParameterMap(), e.getMessage());
        Optional<Throwable> cause = Optional.ofNullable(e.getCause());
        String errorMessage;
        if (cause.isPresent()) {
            logger.warn("Caused by: {}, {}", cause.get().getClass().getName(), cause.get().getMessage());
            errorMessage = cause.get().getMessage();
        } else {
            errorMessage = e.getMessage();
        }
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(e, errorMessage, request.getRequestURI()));
    }

    @SuppressWarnings("unchecked")
    @org.springframework.web.bind.annotation.ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest request, CommandExecutionException exception) {

        logger.warn("Request api call failed. URL: {}, params: {}, error: {}", request.getRequestURL(), RequestParamUtil.paramToStr(request.getParameterMap()), exception.getMessage());
        Optional<Throwable> error = Optional.ofNullable(exception.getCause());
        if (error.isPresent()) {
            logger.warn("Caused by: {}, {}", error.get().getClass().getName(), error.get().getMessage());
            return error.map(e -> {
                if (e instanceof ConcurrencyException) {
                    logger.warn("DUPLICATED ENTITY Request URL: {} ", request.getRequestURL());
                    return new ResponseEntity(ErrorResponse.conflict(e, request.getRequestURI()), HttpStatus.CONFLICT);
                }
                return ResponseEntity.badRequest().body(ErrorResponse.badRequest(e, e.getMessage(), request.getRequestURI()));
            }).get();
        } else {
            return ResponseEntity.badRequest().body(ErrorResponse.badRequest(exception, request.getRequestURI()));
        }
    }

    @SuppressWarnings("unchecked")
    @org.springframework.web.bind.annotation.ExceptionHandler(ConcurrencyException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest request, ConcurrencyException exception) {
        return new ResponseEntity(ErrorResponse.conflict(exception, request.getRequestURI()), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(){}

}
