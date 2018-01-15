package app.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
@ControllerAdvice
public class ExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(){}
}
