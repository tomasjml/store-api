package com.dialexa.storeapi.middleware;

import com.dialexa.storeapi.entities.records.ErrorResponseRecord;
import jakarta.persistence.EntityNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(getErrorDto(ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIlegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(getErrorDto(ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        // Check if exception was already handled in the controller
        if (ex instanceof ResponseStatusException) {
            throw ex;
        }
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        log.error("GLOBAL ERROR HANDLER. message: {}, stackTrace: {}", ex.getMessage(), exceptionAsString);
        return new ResponseEntity<>(
                getErrorDto(ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponseRecord error = new ErrorResponseRecord("Validation Failed", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    protected ErrorResponseRecord getErrorDto(String errMessage, String localizedMessage) {
        List<String> details = new ArrayList<>();
        details.add(localizedMessage);
        return new ErrorResponseRecord(errMessage, details);
    }
}
