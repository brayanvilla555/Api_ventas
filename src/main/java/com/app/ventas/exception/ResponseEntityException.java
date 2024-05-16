package com.app.ventas.exception;

import com.app.ventas.exception.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

    //private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityException.class);

    @ExceptionHandler(ApiErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDto> handleApiErrorException(ApiErrorException ex, HttpServletRequest httpServletRequest) {

        //podemos sacar donde se genera el error
        String errorMessage = ex.getClass().getSimpleName();
        //Al hacer esto podemos sacar la traza completa
        String stackTrace = getStackTraceString(ex);

        ApiErrorDto exception = new ApiErrorDto(
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().toString()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    private String getStackTraceString(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    //controlamos las excepciones de @valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
