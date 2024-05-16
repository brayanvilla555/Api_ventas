package com.app.ventas.exception;

import com.app.ventas.exception.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> exception(Exception exception,
                                                 HttpServletRequest httpServletRequest,
                                                 WebRequest webRequest) {

        exception.printStackTrace();
        if(exception instanceof HttpClientErrorException) {
            return this.handlerHttpClientErrorException((HttpClientErrorException)exception, httpServletRequest, webRequest);

        }else if(exception instanceof AccessDeniedException){
            return this.handleAccessDeniedException((AccessDeniedException)exception, httpServletRequest, webRequest);

        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            return this.handleAuthenticationCredentialsNotFoundException((AuthenticationCredentialsNotFoundException)exception, httpServletRequest, webRequest);
        }else{
            return this.handleGenericException(exception, httpServletRequest, webRequest);
        }
    }

    private ResponseEntity<ApiErrorDto> handleGenericException(Exception exception,
                                                               HttpServletRequest httpServletRequest,
                                                               WebRequest webRequest) {
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setMessage("Error inesperado vuelva a intentarlo");
        apiErrorDto.setBackMessage(exception.getMessage());
        apiErrorDto.setMethod(httpServletRequest.getMethod());
        apiErrorDto.setUrl(httpServletRequest.getRequestURL().toString());

        return  ResponseEntity.internalServerError().body(apiErrorDto);

    }

    private ResponseEntity<ApiErrorDto> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception,
                                                                                         HttpServletRequest httpServletRequest,
                                                                                         WebRequest webRequest) {
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setMessage("No tienes acceso a este recurso");
        apiErrorDto.setBackMessage(exception.getMessage());
        apiErrorDto.setMethod(httpServletRequest.getMethod());
        apiErrorDto.setUrl(httpServletRequest.getRequestURL().toString());

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDto> handleAccessDeniedException(AccessDeniedException exception,
                                                                    HttpServletRequest httpServletRequest,
                                                                    WebRequest webRequest) {
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setMessage("No tienes acceso a este recurso");
        apiErrorDto.setBackMessage(exception.getMessage());
        apiErrorDto.setMethod(httpServletRequest.getMethod());
        apiErrorDto.setUrl(httpServletRequest.getRequestURL().toString());

        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDto> handlerHttpClientErrorException(HttpClientErrorException exception,
                                                                        HttpServletRequest httpServletRequest,
                                                                        WebRequest webRequest) {
        String message;
        if(exception instanceof HttpClientErrorException.Forbidden){
            message = "No cuentas con acceso a este recurso";
        } else if (exception instanceof  HttpClientErrorException.Unauthorized) {
            message = "No as iniciado sesión para este recurso";
        } else if (exception instanceof  HttpClientErrorException.NotFound) {
            message = "Recurso no encontrado";
        } else if (exception instanceof HttpClientErrorException.Conflict) {
            message = "Hubo problemas al procesar la petición";
        }else{
            message = "Error inesperado al realizar consulta";
        }
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setMessage(message);
        apiErrorDto.setBackMessage(exception.getMessage());
        apiErrorDto.setMethod(httpServletRequest.getMethod());
        apiErrorDto.setUrl(httpServletRequest.getRequestURL().toString());

        return  ResponseEntity.status(exception.getStatusCode()).body(apiErrorDto);
    }
}
