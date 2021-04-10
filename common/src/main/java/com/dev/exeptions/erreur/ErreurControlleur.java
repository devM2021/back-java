package com.dev.exeptions.erreur;

import com.dev.exeptions.AppResponseEntityException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@ControllerAdvice
@Setter
public class ErreurControlleur {
    private List<String> errors = new ArrayList<>();
    private final AppHandlingError error = new AppHandlingError();

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<AppHandlingError> ViolationInegrity(DataIntegrityViolationException ex) {
        error.setMessage("This record already exist");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setListErrors(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<AppHandlingError> handleConstraintViolation(
            BindException ex, WebRequest request) {

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        AppHandlingError error = new AppHandlingError(HttpStatus.CONFLICT, "Please check data format", errors);
        log.error("Format des donnees incorrect {}", error.toString());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler({AppResponseEntityException.class})
    public ResponseEntity<AppHandlingError> handleResponseError(HttpServletRequest req, Exception ex) {
        log.error("Request response entity: " + req.getRequestURL() + " raised " + ex);
        errors.add(ex.getLocalizedMessage());
        AppHandlingError error = new AppHandlingError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(error, error.getStatus());
    }
}




